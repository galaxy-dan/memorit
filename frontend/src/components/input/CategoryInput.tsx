import React, { ReactNode, useState } from 'react';
import { containerCss, iconCss, inputCss } from './inputCSS';
import { useRecoilState } from 'recoil';
import { addMemoryState, errorState } from '@/store/memory';
import { addMemoryType } from '@/model/memory';
import { errorType } from '@/model/error';
import AlertMessage from './AlertMessage';

type Props = {
  placeholder?: string;
  icon?: ReactNode;
  className?: string;
};

export default function CategoryInput({ placeholder, icon, className }: Props) {
  const [memory, setMemory] = useRecoilState<addMemoryType>(addMemoryState);
  const [error, setError] = useRecoilState<errorType>(errorState);

  return (
    <div>
      <div className="border border-white">
        <div
          className={
            containerCss + ' flex items-center border relative ' + className
          }
        >
          <div className={`${iconCss(false, false)}`}>{icon}</div>
          <input
            type="text"
            className={inputCss}
            placeholder={placeholder}
            value={memory.category}
            readOnly={true}
          />
          <AlertMessage>{error.category}</AlertMessage>
        </div>
      </div>
      
    </div>
  );
}
