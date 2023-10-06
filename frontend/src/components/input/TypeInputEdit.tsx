import React, { ReactNode, useEffect, useState } from 'react';
import { containerCss, iconCss, inputCss } from './inputCSS';
import { useRecoilState } from 'recoil';
import { addMemoryType, showMenuType } from '@/model/memory';
import { addMemoryState } from '@/store/memory';


type Props = {
  type?: string;
  placeholder?: string;
  icon?: ReactNode;
};

export default function TypeInputNoEdit({ icon }: Props) {
  const [memory, setMemory] = useRecoilState<addMemoryType>(addMemoryState);

  return (
    <div>
      <div className="border border-white">
        <div className={containerCss + ' flex items-center border relative'}>
          <div className={`${iconCss(false, false)}`}>{icon}</div>
          <input
            type="text"
            className={`${inputCss} w-full`}
            value={memory.type}
            readOnly={true}
          />
        </div>
      </div>
    </div>
  );
}
