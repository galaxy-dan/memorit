import { addMemoryType } from '@/model/memory';
import { addMemoryState } from '@/store/memory';
import { ReactNode, useState } from 'react';
import { useRecoilState } from 'recoil';
import { containerCss, iconCss } from './inputCSS';

type Props = {
  placeholder?: string;
  icon?: ReactNode;
  className?: string;
};

export default function MoneyInput({ placeholder, icon, className }: Props) {
  const [memory, setMemory] = useRecoilState<addMemoryType>(addMemoryState);
  const [isFocused, setIsFocused] = useState<boolean>(false);
  const [isTouched, setIsTouched] = useState<boolean>(false);

  return (
    <div
      className="flex w-full pl-2 items-center relative"
      onClick={() => {
        setMemory((prev) => ({ ...prev, isMoney: true }));
      }}
    >
      <input
        id="isMoney"
        type="radio"
        className="accent-pink-500 w-5 h-5"
        checked={memory.isMoney}
        readOnly={true}
      ></input>
      <div
        className={
          containerCss + ' flex items-center border relative ' + className
        }
      >
        <div className={iconCss(isFocused, isTouched)}>{icon}</div>
        <input
          type="text"
          className={`w-full text-lg ${!memory.isMoney && 'text-gray-300'}`}
          placeholder={placeholder}
          value={memory.money === 0 ? '' : memory.money.toString()}
          readOnly={!memory.isMoney}
          onChange={(e) => {
            let num: number = Number(
              e.target.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1'),
            );
            setMemory((prev) => ({ ...prev, money: num }));
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
  );
}
