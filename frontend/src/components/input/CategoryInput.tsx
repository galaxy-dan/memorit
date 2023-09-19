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

export default function CategoryInput({ type, placeholder, icon }: Props) {
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
        {!memory.categorySelected && memory.category !== '' && (
          <div className="w-8/12 rounded-xl bg-white absolute top-12 right-1 z-30 shadow-[0_0_2px_2px_rgba(0,0,0,0.1)] ">
            {memory.categoryList.map((item, index) => (
              <p
                className="text-lg px-5 py-3 truncate"
                key={index}
                onClick={() =>
                  setMemory((prev) => ({
                    ...prev,
                    category: item,
                    categorySelected: true,
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
                  categorySelected: true,
                }))
              }
            >
              {memory.category} 더하기 (없을 때)
            </p>
          </div>
        )}
      </div>
    </>
  );
}
