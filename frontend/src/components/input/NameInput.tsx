import React, { ReactNode, useState } from 'react';
import SearchList from './searchList';
import { containerCss, iconCss } from './inputCSS';
import { useRecoilState } from 'recoil';
import { addMemory } from '@/model/memory';
import { addMemoryState } from '@/store/memory';

type Props = {
  type: string;
  placeholder?: string;
  icon?: ReactNode;
};

export default function NameInput({ type, placeholder, icon }: Props) {
  const [isFocused, setIsFocused] = useState<boolean>(false);
  const [isTouched, setIsTouched] = useState<boolean>(false);

  const [memory, setMemory] = useRecoilState<addMemory>(addMemoryState);

  return (
    <>
      <div className={containerCss + ' flex items-center border relative'}>
        <div className={iconCss(isFocused, isTouched)}>{icon}</div>
        <input
          type="text"
          className={`w-full text-lg ${
            memory.nameSelected ? 'text-black' : 'text-gray-400'
          }`}
          placeholder={placeholder}
          value={memory.name}
          onChange={(e) => {
            setMemory((prev) => ({
              ...prev,
              name: e.target.value,
              nameSelected: false,
            }));
            setMemory((prev) => ({ ...prev, nameList: ['1', '2', '3'] }));
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
        {!memory.nameSelected && memory.name !== '' && (
          <div className="w-8/12 rounded-xl bg-white absolute top-12 right-1 z-30 shadow-[0_0_2px_2px_rgba(0,0,0,0.1)] ">
            {memory.nameList.map((item, index) => (
              <p
                className="text-lg px-5 py-3 truncate"
                key={index}
                onClick={() =>
                  setMemory((prev) => ({
                    ...prev,
                    name: item,
                    nameSelected: true,
                  }))
                }
              >
                {item}
              </p>
            ))}
            <p
              className="text-lg px-5 py-3 truncate"
              onClick={() =>
                setMemory((prev) => ({
                  ...prev,
                  nameSelected: true,
                }))
              }
            >
              {memory.name} 더하기 (없을 때)
            </p>
          </div>
        )}
      </div>
    </>
  );
}
