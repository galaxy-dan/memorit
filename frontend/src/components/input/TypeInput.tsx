import React, { ReactNode, useEffect, useState } from 'react';
import { containerCss, iconCss } from './inputCSS';
import { useRecoilState } from 'recoil';
import { addMemoryType, showMenuType } from '@/model/memory';
import { addMemoryState, errorState, showMenuState } from '@/store/memory';
import { motion } from 'framer-motion';
import {
  UseQueryResult,
  useQuery,
  useQueryClient,
} from '@tanstack/react-query';
import { errorType } from '@/model/error';
import AlertMessage from './AlertMessage';
import { getTypeListByName } from '@/service/api/type';
import { addType } from '@/service/api/type';

type Props = {
  type: string;
  placeholder?: string;
  icon?: ReactNode;
};

type TypeList = {
  typeList: string[];
};

export default function TypeInput({ type, placeholder, icon }: Props) {
  const [isFocused, setIsFocused] = useState<boolean>(false);
  const [isTouched, setIsTouched] = useState<boolean>(false);
  const [typeInput, setTypeInput] = useState<string>('');

  const [memory, setMemory] = useRecoilState<addMemoryType>(addMemoryState);
  const [showMenu, setShowMenu] = useRecoilState<showMenuType>(showMenuState);
  const [error, setError] = useRecoilState<errorType>(errorState);

  const { data: typeList }: UseQueryResult<TypeList> = useQuery({
    queryKey: ['type', memory.type],
    queryFn: () => getTypeListByName(memory.type),
  });

  const queryClient = useQueryClient();

  

  function doShowMenu() {
    setShowMenu((prev) => ({ ...prev, showTypeMenu: true , showNameMenu: false }));

  }

  const addTypeAsync = async (type: string) => {
    await addType(type);
    queryClient.invalidateQueries({ queryKey: ['type'] });
  };

  useEffect(() => {
    const debounce = setTimeout(() => {
      return setMemory((prev) => ({
        ...prev,
        type: typeInput,
      }));
    }, 180);
    return () => clearTimeout(debounce);
  }, [typeInput, setMemory]);

  return (
    <div>
      <div className="border">
        <div className={containerCss + ' flex items-center border relative'}>
          <div className={iconCss(isFocused, isTouched)}>{icon}</div>
          <input
            type="text"
            className={`w-full text-lg ${
              memory.typeSelected ? 'text-black' : 'text-gray-400'
            }`}
            placeholder={placeholder}
            value={typeInput}
            onChange={(e) => {
              setTypeInput(e.target.value);
              setError((prev) => ({...prev, type:''}));
            }}
            onKeyDown={() => {
              setMemory((prev) => ({
                ...prev,
                typeSelected: false,
              }));
            }}
            onFocus={() => {
              setIsFocused(true);
              doShowMenu();
            }}
            onBlur={(e) => {
              setIsFocused(false);
            }}
            onTouchStart={() => {
              setIsTouched(true);
            }}
            onTouchEnd={() => {
              setIsTouched(false);
            }}
            onClick={(e) => {
              e.stopPropagation();
            }}
          />
          {showMenu.showTypeMenu && (
            <div className="w-8/12 max-h-52 overflow-scroll rounded-xl bg-white absolute top-12 right-1 z-30 shadow-[0_0_2px_2px_rgba(0,0,0,0.1)]">
              {typeList?.typeList.map((item, index) => (
                <motion.p
                  className={`text-lg px-5 ${
                    index === 0 && 'pt-5 rounded-t-xl'
                  } py-3 truncate`}
                  key={index}
                  onClick={() => {
                    setTypeInput(item);
                    setMemory((prev) => ({
                      ...prev,
                      type: item,
                      typeSelected: true,
                    }));
                  }}
                  whileTap={{
                    backgroundColor: '#D0D0D0',
                  }}
                >
                  {item}
                </motion.p>
              ))}
              {memory.type !== '' && !typeList?.typeList.includes(memory.type) && (
                <motion.p
                  className="text-lg px-5 pt-3 pb-5 truncate rounded-b-xl"
                  onClick={() => {
                    setMemory((prev) => ({
                      ...prev,
                      typeSelected: true,
                    }));
                    setTypeInput(memory.type);
                    addTypeAsync(memory.type);
                  }}
                  whileTap={{
                    backgroundColor: '#D0D0D0',
                  }}
                >
                  추가 : &nbsp;&quot;{memory.type}&quot;
                </motion.p>
              )}
            </div>
          )}
        </div>
      </div>
      <AlertMessage>{error.type}</AlertMessage>
    </div>
  );
}
