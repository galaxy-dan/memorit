import { addMemoryType } from '@/model/memory';
import { ReactNode, useState } from 'react';
import { containerCss, iconCss, inputCss } from './inputCSS';
import { errorType } from '@/model/error';
import AlertMessage from './AlertMessage';
import { inputValid } from '@/service/input';
import { useMemoryStore } from '@/store/memory';

type Props = {
  placeholder?: string;
  icon?: ReactNode;
  className?: string;
};

export default function MoneyInput({ placeholder, icon, className }: Props) {
  const { memory, setMemory } = useMemoryStore();
  const { error, setError } = useMemoryStore();
  const [isFocused, setIsFocused] = useState<boolean>(false);
  const [isTouched, setIsTouched] = useState<boolean>(false);

  return (
    <div>
      <div
        className="flex w-full pl-2 items-center relative"
        onClick={() => {
          setMemory({ ...memory, isMoney: true });
          setError({ ...error, present: '' });
        }}
      >
        <input
          id="isMoney"
          type="radio"
          className="accent-blue-500 w-5 h-5 ml-5"
          checked={memory.isMoney}
          readOnly={true}
        ></input>
        <div
          className={
            containerCss +
            ' first:mx-0 flex items-center border relative ' +
            className
          }
        >
          <div className={iconCss(isFocused, isTouched)}>{icon}</div>
          <input
            type="text"
            className={`${inputCss} ${!memory.isMoney && 'text-gray-300'}`}
            placeholder={placeholder}
            value={memory.money === 0 ? '' : memory.money.toString()}
            readOnly={!memory.isMoney}
            onChange={(e) => {
              let num: number = Number(
                e.target.value
                  .replace(/[^0-9.]/g, '')
                  .replace(/(\..*)\./g, '$1'),
              );
              if (num > inputValid.money.maxSize) {
                num = inputValid.money.maxSize;
              }
              setMemory({ ...memory, money: num });
              setError({ ...error, present: '', money: '' });
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
      <AlertMessage>{error.money}</AlertMessage>
    </div>
  );
}
