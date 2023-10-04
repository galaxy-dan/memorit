import React, { ReactNode, useRef, useState } from 'react';
import { containerCss, iconCss } from './inputCSS';
import { useRecoilState } from 'recoil';
import { addMemoryType } from '@/model/memory';
import { addMemoryState, errorState } from '@/store/memory';
import { errorType } from '@/model/error';
import AlertMessage from './AlertMessage';

type Props = {
  placeholder?: string;
  icon?: ReactNode;
};

export default function MemoInput({ placeholder, icon }: Props) {
  const [memory, setMemory] = useRecoilState<addMemoryType>(addMemoryState);
  const [isFocused, setIsFocused] = useState<boolean>(false);
  const [isTouched, setIsTouched] = useState<boolean>(false);
  const ref = useRef<HTMLTextAreaElement>(null);
  const [error, setError] = useRecoilState<errorType>(errorState);

  const handleResizeHeight = () => {
    if (ref && ref.current) {
      ref.current.style.height = 'auto';
      ref.current.style.height = ref.current.scrollHeight + 'px';
    }
  };
  return (
    <div>
      <div className="border border-white">
        <div className={containerCss + ' flex items-start border relative'}>
          <div className={iconCss(isFocused, isTouched)}>{icon}</div>
          <textarea
            className="w-full text-l resize-none"
            placeholder={placeholder}
            value={memory.memo}
            rows={2}
            
            ref={ref}
            onChange={(e) => {
              setMemory((prev) => ({ ...prev, memo: e.target.value }));
              setError((prev) => ({ ...prev, memo: '' }));
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
      </div>
      <AlertMessage>{error.memo}</AlertMessage>
    </div>
  );
}
