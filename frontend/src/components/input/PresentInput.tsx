import { addMemoryType } from '@/model/memory';
import { addMemoryState, errorState } from '@/store/memory';
import { ReactNode, useState } from 'react';
import { useRecoilState } from 'recoil';
import { containerCss, iconCss } from './inputCSS';
import { errorType } from '@/model/error';
import AlertMessage from './AlertMessage';

type Props = {
  placeholder?: string;
  icon?: ReactNode;
  className?: string;
};

export default function PresentInput({ placeholder, icon, className }: Props) {
  const [memory, setMemory] = useRecoilState<addMemoryType>(addMemoryState);
  const [isFocused, setIsFocused] = useState<boolean>(false);
  const [isTouched, setIsTouched] = useState<boolean>(false);

  function CheckPresent() {
    // 선물 : 반필수, 20자 이하, 1자 이상
    if (!memory.isMoney) {
      if (memory.present.length > 20) {
        setError((prev) => ({
          ...prev,
          present: '최대 글자 수는 20자 입니다.',
        }));
        return false;
      } else if (memory.present && memory.present.length === 0) {
        setError((prev) => ({ ...prev, present: '선물을 입력해주세요' }));
        return false;
      }
    }
    setError((prev) => ({ ...prev, present: '', money: '' }));
    return true;
  }

  const [error, setError] = useRecoilState<errorType>(errorState);
  const onChange = () => {
    CheckPresent();
  };
  return (
    <div>
      <div
        className="flex w-full pl-2 items-center relative"
        onClick={() => {
          setMemory((prev) => ({ ...prev, isMoney: false }));
          setError((prev) => ({ ...prev, money: '' }));
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
            className={`w-full text-lg ${memory.isMoney && 'text-gray-300'}`}
            placeholder={placeholder}
            value={memory.present}
            readOnly={memory.isMoney}
            onChange={(e) => {
              setMemory((prev) => ({ ...prev, present: e.target.value }));
              setError((prev) => ({ ...prev, present: '', money: '' }));
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
