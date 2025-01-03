import React, { ReactNode, useEffect, useState } from 'react';
import { containerCss, iconCss, inputCss } from './inputCSS';
import { addMemoryType, showMenuType } from '@/model/memory';
import { motion } from 'framer-motion';
import {
  UseQueryResult,
  useQuery,
  useQueryClient,
} from '@tanstack/react-query';
import { friendList } from '@/model/friend';
import { addFriend, getFriendListByName } from '@/service/api/friend';
import { errorType } from '@/model/error';
import AlertMessage from './AlertMessage';
import { useMemoryStore } from '@/store/memory';

type Props = {
  type: string;
  placeholder?: string;
  icon?: ReactNode;
};

export default function NameInput({ type, placeholder, icon }: Props) {
  const [isFocused, setIsFocused] = useState<boolean>(false);
  const [isTouched, setIsTouched] = useState<boolean>(false);
  const [nameInput, setNameInput] = useState<string>('');

  const { memory, setMemory } = useMemoryStore();
  const { showMenu, setShowMenu } = useMemoryStore();
  const { error, setError } = useMemoryStore();

  const { data: friendList }: UseQueryResult<friendList> = useQuery({
    queryKey: ['friend', memory.name],
    queryFn: () => getFriendListByName(memory.name),
  });

  const queryClient = useQueryClient();

  function doShowMenu() {
    setShowMenu({
      showNameMenu: true,
      showTypeMenu: false,
    });
  }

  const addFriendAsync = async (name: string, category: string | null) => {
    // 여기서 추가한 친구 ID 받기
    const friendId = await addFriend(name, category);
    setMemory({ ...memory, friendID: friendId, category: '미지정' });
    queryClient.invalidateQueries({ queryKey: ['friend'] });
  };

  useEffect(() => {
    const debounce = setTimeout(() => {
      setMemory({ ...memory, name: nameInput });
    }, 180);
    return () => clearTimeout(debounce);
  }, [nameInput, setMemory]);

  return (
    <div>
      <div className="border border-white">
        <div className={containerCss + ' flex items-center border relative'}>
          <div className={iconCss(isFocused, isTouched)}>{icon}</div>
          <input
            type="text"
            className={`${inputCss} ${
              memory.nameSelected ? 'text-black' : 'text-gray-400'
            }`}
            placeholder={placeholder}
            value={nameInput}
            onChange={(e) => {
              setNameInput(e.target.value);
              setMemory({ ...memory, category: '미지정' });
              setError({ ...error, name: '' });
            }}
            onKeyDown={() => {
              if (memory.nameSelected) {
                setMemory({
                  ...memory,
                  nameSelected: false,
                });
              }
            }}
            onFocus={() => {
              setIsFocused(true);
              doShowMenu();
            }}
            onBlur={(e) => {
              setIsFocused(false);
            }}
            onTouchStart={() => {
              setIsTouched(true);
            }}
            onTouchEnd={() => {
              setIsTouched(false);
            }}
            onClick={(e) => {
              e.stopPropagation();
            }}
          />
          {showMenu.showNameMenu && (
            <div className="w-8/12 max-h-52 overflow-scroll rounded-xl bg-white absolute top-12 right-1 z-30 shadow-[0_0_2px_2px_rgba(0,0,0,0.1)]">
              {friendList?.list.map((item, index) => (
                <motion.p
                  className={`${inputCss} px-5 ${
                    index === 0 && 'pt-5 rounded-t-xl'
                  } py-3 truncate`}
                  key={index}
                  onClick={() => {
                    setNameInput(item.name);
                    setMemory({
                      ...memory,
                      name: item.name,
                      nameSelected: true,
                      category:
                        item.category === null ? '미지정' : item.category,
                      friendID: item.friendId,
                    });
                    setError({ ...error, name: '' });
                  }}
                  whileTap={{
                    backgroundColor: '#D0D0D0',
                  }}
                >
                  {item.name}
                </motion.p>
              ))}
              {memory.name !== '' && (
                <motion.p
                  className={`${inputCss} px-5 pt-3 pb-5 truncate rounded-b-xl`}
                  onClick={() => {
                    setMemory({
                      ...memory,
                      nameSelected: true,
                    });
                    setNameInput(memory.name);
                    addFriendAsync(memory.name, null);
                    setError({ ...error, name: '' });
                    // 여기서 받은 ID로 설정하기
                  }}
                  whileTap={{
                    backgroundColor: '#D0D0D0',
                  }}
                >
                  추가 : &nbsp;&quot;{memory.name}&quot;
                </motion.p>
              )}
            </div>
          )}
        </div>
      </div>
      <AlertMessage>{error.name}</AlertMessage>
    </div>
  );
}
