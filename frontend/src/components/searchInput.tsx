import React, { ReactNode, useState } from 'react';
import SearchList from './searchList';
import { containerCss, iconCss } from '../styles/inputCSS';

type Props = {
  type: string;
  placeholder?: string;
  icon?: ReactNode;
};

export default function SearchInput({ type, placeholder, icon }: Props) {
  const [list, setList] = useState<Array<string>>([]);
  const [inputText, setInputText] = useState<string>('');
  const [isSelected, setIsSelected] = useState<boolean>(false);
  const [isFocused, setIsFocused] = useState<boolean>(false);
  const [isTouched, setIsTouched] = useState<boolean>(false);

  return (
    <>
      <div className={containerCss() + ' flex items-center border relative'}>
        <div className={iconCss(isFocused, isTouched)}>{icon}</div>
        <input
          type="text"
          className={`w-full text-lg ${
            isSelected ? 'text-black' : 'text-gray-400'
          }`}
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
        <SearchList list={list} />
      </div>
    </>
  );
}
