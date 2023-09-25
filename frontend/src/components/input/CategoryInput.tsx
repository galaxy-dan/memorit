import React, { ReactNode, useState } from 'react';
import { containerCss, iconCss } from './inputCSS';
import { useRecoilState } from 'recoil';
import { addMemoryType, showMenuType } from '@/model/memory';
import { addMemoryState, showMenuState } from '@/store/memory';
import { motion } from 'framer-motion';

type Props = {
  type: string;
  placeholder?: string;
  icon?: ReactNode;
};

export default function CategoryInput({ type, placeholder, icon }: Props) {
  const [isFocused, setIsFocused] = useState<boolean>(false);
  const [isTouched, setIsTouched] = useState<boolean>(false);

  const [memory, setMemory] = useRecoilState<addMemoryType>(addMemoryState);
  const [showMenu, setShowMenu] = useRecoilState<showMenuType>(showMenuState);

  function doHideMenu() {
    setShowMenu((prev) => ({ ...prev, showCategoryMenu: false }));
  }

  function doShowMenu() {
    setShowMenu((prev) => ({ ...prev, showCategoryMenu: true }));
  }

  return (
    <div className='border'>
      <div className={containerCss + ' flex items-center relative'}>
        <div className={iconCss(isFocused, isTouched)}>{icon}</div>
        <input
          type="text"
          className={`w-full text-lg ${
            memory.categorySelected ? 'text-black' : 'text-gray-400'
          }`}
          placeholder={placeholder}
          value={memory.category}
          onChange={(e) => {
            setMemory((prev) => ({
              ...prev,
              category: e.target.value,
              categorySelected: false,
            }));
            setMemory((prev) => ({ ...prev, categoryList: ['1', '2', '3'] }));
            e.target.value === '' ? doHideMenu() : doShowMenu();
          }}
          onFocus={() => {
            setIsFocused(true);
          }}
          onBlur={() => {
            setIsFocused(false);
          }}
          onTouchStart={() => {
            setIsTouched(true);
          }}
          onTouchEnd={() => {
            setIsTouched(false);
          }}
        />
        {showMenu.showCategoryMenu && (
          <div className="w-8/12 rounded-xl bg-white absolute top-12 right-1 z-30 shadow-[0_0_2px_2px_rgba(0,0,0,0.1)]">
            {memory.categoryList.map((item, index) => (
              <motion.p
                className={`text-lg px-5 ${
                  index === 0 && 'pt-5 rounded-t-xl'
                } py-3 truncate`}
                key={index}
                onClick={() =>
                  setMemory((prev) => ({
                    ...prev,
                    category: item,
                    categorySelected: true,
                  }))
                }
                whileTap={{
                  backgroundColor: '#D0D0D0',
                }}
              >
                {item}
              </motion.p>
            ))}
            <motion.p
              className="text-lg px-5 pt-3 pb-5 truncate rounded-b-xl"
              onClick={() =>
                setMemory((prev) => ({
                  ...prev,
                  categorySelected: true,
                }))
              }
              whileTap={{
                backgroundColor: '#D0D0D0',
              }}
            >
              추가 : &nbsp;&quot;{memory.category}&quot;
            </motion.p>
          </div>
        )}
      </div>
    </div>
  );
}
