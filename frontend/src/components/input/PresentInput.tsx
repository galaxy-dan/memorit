import { addMemoryType } from '@/model/memory';
import { ReactNode, useState } from 'react';
import { containerCss, iconCss, inputCss } from './inputCSS';
import { errorType } from '@/model/error';
import AlertMessage from './AlertMessage';
import { useMemoryStore } from '@/store/memory';

type Props = {
  placeholder?: string;
  icon?: ReactNode;
  className?: string;
};

export default function PresentInput({ placeholder, icon, className }: Props) {
  const { memory, setMemory } = useMemoryStore();
  const { error, setError } = useMemoryStore();
  const [isFocused, setIsFocused] = useState<boolean>(false);
  const [isTouched, setIsTouched] = useState<boolean>(false);

  function CheckPresent() {
    // 선물 : 반필수, 20자 이하, 1자 이상
    if (!memory.isMoney) {
      if (memory.present.length > 20) {
        setError({ ...error, present: '최대 글자 수는 20자 입니다.' });
        return false;
      } else if (memory.present && memory.present.length === 0) {
        setError({ ...error, present: '선물을 입력해주세요' });
        return false;
      }
    }
    setError({ ...error, present: '', money: '' });
    return true;
  }

  const onChange = () => {
    CheckPresent();
  };

  return (
    <div>
      <div
        className="flex w-full pl-2 items-center relative"
        onClick={() => {
          setMemory({ ...memory, isMoney: false });
          setError({ ...error, money: '' });
        }}
        onChange={onChange}
      >
        <input
          id="isPresent"
          type="radio"
          className="accent-blue-500 w-5 h-5 ml-5"
          checked={!memory.isMoney}
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
            className={`${inputCss} ${memory.isMoney && 'text-gray-300'}`}
            placeholder={placeholder}
            value={memory.present}
            readOnly={memory.isMoney}
            onChange={(e) => {
              setMemory({ ...memory, present: e.target.value });
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
      <AlertMessage>{error.present}</AlertMessage>
    </div>
  );
}
