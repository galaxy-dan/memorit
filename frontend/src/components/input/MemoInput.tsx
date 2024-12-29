import React, { ReactNode, useRef, useState } from 'react';
import { containerCss, iconCss, inputCss } from './inputCSS';
import { addMemoryType } from '@/model/memory';
import { errorType } from '@/model/error';
import AlertMessage from './AlertMessage';
import { useMemoryStore } from '@/store/memory';

type Props = {
  placeholder?: string;
  icon?: ReactNode;
};

export default function MemoInput({ placeholder, icon }: Props) {
  const { memory, setMemory } = useMemoryStore();
  const { error, setError } = useMemoryStore();
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
    <div>
      <div className="border border-white">
        <div className={containerCss + ' flex items-start border relative'}>
          <div className={iconCss(isFocused, isTouched)}>{icon}</div>
          <textarea
            className={inputCss+" resize-none"}
            placeholder={placeholder}
            value={memory.memo}
            rows={2}
            ref={ref}
            onChange={(e) => {
              setMemory({ ...memory, memo: e.target.value });
              setError({ ...error, memo: '' });
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
