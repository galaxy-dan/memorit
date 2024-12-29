import React, { useState } from 'react';
import { containerCss, iconCss } from '../input/inputCSS';
import { BsPerson } from 'react-icons/bs';
import { motion } from 'framer-motion';
import {
  UseQueryResult,
  useQuery,
  useQueryClient,
} from '@tanstack/react-query';
import { friendList } from '@/model/friend';
import { addFriend, getFriendListByName } from '@/service/api/friend';
import { useMemoryStore } from '@/store/memory'; // Zustand store import

export default function FriendNameInput(props: any) {
  const [isFocused, setIsFocused] = useState<boolean>(false);
  const [isTouched, setIsTouched] = useState<boolean>(false);

  const memory = useMemoryStore((state) => state.memory);

  return (
    <div className="border">
      <div className={containerCss + ' flex items-center border relative'}>
        <div className={iconCss(isFocused, isTouched)}>
          <BsPerson />
        </div>
        <input
          type="text"
          placeholder="이름"
          className={`w-full text-lg ${
            memory.nameSelected ? 'text-black' : 'text-gray-400'
          }`}
          {...props.props}
        />
      </div>
    </div>
  );
}
