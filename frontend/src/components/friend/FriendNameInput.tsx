import React, { ReactNode, useEffect, useState } from 'react';
import { containerCss, iconCss } from '../input/inputCSS';
import { useRecoilState } from 'recoil';
import { addMemoryType, showMenuType } from '@/model/memory';
import { addMemoryState, showMenuState } from '@/store/memory';
import { BsPerson } from 'react-icons/bs';
import { motion } from 'framer-motion';
import {
  UseQueryResult,
  useQuery,
  useQueryClient,
} from '@tanstack/react-query';
import { friendList } from '@/model/friend';
import { addFriend, getFriendListByName } from '@/service/api/friend';

export default function FriendNameInput(props: any) {
  const [isFocused, setIsFocused] = useState<boolean>(false);
  const [isTouched, setIsTouched] = useState<boolean>(false);

  const [memory, setMemory] = useRecoilState<addMemoryType>(addMemoryState);

  return (
    <div className="border">
      <div className={containerCss + ' flex items-center border relative'}>
        <div className={iconCss(isFocused, isTouched)}>
          <BsPerson />
        </div>
        <input
          type="text"
          placeholder='이름'
          className={`w-full text-lg ${
            memory.nameSelected ? 'text-black' : 'text-gray-400'
          }`}
          // onFocus={() => {
          //   setIsFocused(true);
          // }}
          // onBlur={(e) => {
          //   setIsFocused(false);
          // }}
          // onTouchStart={() => {
          //   setIsTouched(true);
          // }}
          // onTouchEnd={() => {
          //   setIsTouched(false);
          // }}
          // onClick={(e) => {
          //   e.stopPropagation();
          // }}
          {...props.props}
        />
      </div>
    </div>
  );
}
