import React, { ReactNode, useState } from 'react';
import { containerCss, iconCss } from './inputCSS';

type Props = {
  placeholder?: string;
  icon?: ReactNode;
};

export default function TextInput({ placeholder, icon }: Props) {
  const [inputText, setInputText] = useState<string>('');
  const [isFocused, setIsFocused] = useState<boolean>(false);
  const [isTouched, setIsTouched] = useState<boolean>(false);
  return (
    <>
      <div className={containerCss() + ' flex items-center border relative'}>
        <div className={iconCss(isFocused, isTouched)}>{icon}</div>
        <input
          type="text"
          className="w-full text-lg"
          placeholder={placeholder}
          value={inputText}
          onChange={(e) => {
            setInputText(e.target.value);
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
