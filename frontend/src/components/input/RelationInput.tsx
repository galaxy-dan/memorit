import React, { ReactNode, useState } from 'react';
import { containerCss, iconCss } from './inputCSS';
import { useRecoilState } from 'recoil';
import { addMemoryState } from '@/store/memory';
import { addMemoryType } from '@/model/memory';

type Props = {
  placeholder?: string;
  icon?: ReactNode;
  className?: string;
};

export default function RelationInput({ placeholder, icon, className }: Props) {
  const [memory, setMemory] = useRecoilState<addMemoryType>(addMemoryState);

  return (
    <>
      <div
        className={
          containerCss + ' flex items-center border relative ' + className
        }
      >
        <div className={`${iconCss(false, false)}`}>{icon}</div>
        <input
          type="text"
          className="w-full text-lg"
          placeholder={placeholder}
          value={memory.relation}
          readOnly={true}
        />
      </div>
    </>
  );
}
