import React, { ReactNode, useRef, useState } from 'react';
import { containerCss, iconCss } from './inputCSS';
import { useRecoilState } from 'recoil';
import { addMemory } from '@/model/memory';
import { addMemoryState } from '@/store/memory';

type Props = {
  placeholder?: string;
  icon?: ReactNode;
};

export default function TextareaInput({ placeholder, icon }: Props) {
  const [memory, setMemory] = useRecoilState<addMemory>(addMemoryState);
  const [isFocused, setIsFocused] = useState<boolean>(false);
  const [isTouched, setIsTouched] = useState<boolean>(false);
  const ref = useRef<HTMLTextAreaElement>(null);
  const handleResizeHeight = () => {
    if (ref && ref.current) {
      ref.current.style.height = 'auto';
      ref.current.style.height = ref.current.scrollHeight + 'px';
    }
  };
  return (
    <>
      <div className={containerCss + ' flex items-start border relative'}>
        <div className={iconCss(isFocused, isTouched)}>{icon}</div>
        <textarea
          className="w-full text-lg"
          placeholder={placeholder}
          value={memory.memo}
          rows={2}
          ref={ref}
          onChange={(e) => {
            setMemory((prev) => ({...prev,memo:e.target.value}));
            handleResizeHeight();
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
      </div>
    </>
  );
}
