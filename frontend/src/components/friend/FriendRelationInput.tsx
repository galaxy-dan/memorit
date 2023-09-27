import React, { ReactNode, useState } from 'react';
import { containerCss, iconCss } from '../input/inputCSS';
import { useRecoilState } from 'recoil';
import { addMemoryState, showMenuState } from '@/store/memory';
import { addMemoryType, showMenuType } from '@/model/memory';
import { motion } from 'framer-motion';
import { Category } from '@/app/friend/page';
import {
  UseQueryResult,
  useQuery,
  useQueryClient,
} from '@tanstack/react-query';
import { get } from 'react-hook-form';

type Props = {
  placeholder?: string;
  icon?: ReactNode;
  className?: string;
  setValue: Function;
  props: any;
};

export default function FriendRelationInput({
  placeholder,
  icon,
  className,
  setValue,
  props
}: Props) {
  const [memory, setMemory] = useRecoilState<addMemoryType>(addMemoryState);
  const [showMenu, setShowMenu] = useState<boolean>(false);
  const queryClient = useQueryClient();
  const categoryData: Category | undefined = queryClient.getQueryData([
    'category',
  ]);
  // const { data: categoryDat }: UseQueryResult<Category> = useQuery({
  //   queryKey: ['category'],
  //   queryFn: () => get('/category'),
  //   refetchInterval: 5000,
  // });
  // const categoryDat:any = [];

  return (
    <div className="border relative">
      <div className={containerCss + ' flex items-center border relative'}>
        <div className={`${iconCss(false, false)}`}>{icon}</div>
        <input
          type="text"
          className="w-full text-lg"
          placeholder={placeholder}
          onFocus={() => setShowMenu(true)}
          {...props}
        />
      </div>
      {showMenu && (
        <div className="w-8/12 max-h-52 overflow-scroll scrollbar-hide rounded-xl bg-white absolute top-12 right-1 z-30 shadow-[0_0_2px_2px_rgba(0,0,0,0.1)]">
          {categoryData?.categoryList?.map((el, index) => (
            <motion.p
              className={`text-lg px-5 ${
                index === 0 && 'pt-5 rounded-t-xl'
              } py-3 truncate`}
              key={index}
              onClick={() => {
                // setMemory((prev) => ({
                //   ...prev,
                //   name: item.name,
                //   nameSelected: true,
                //   relation: item.category === null ? '미지정' : item.category,
                // }));
                // setNameInput(item.name);
                setValue('category', el);
                setShowMenu(false);
              }}
              whileTap={{
                backgroundColor: '#D0D0D0',
              }}
            >
              {el}
            </motion.p>
          ))}
          {memory.name !== '' && (
            <motion.p
              className="text-lg px-5 pt-3 pb-5 truncate rounded-b-xl"
              onClick={() => {
                // setMemory((prev) => ({
                //   ...prev,
                //   nameSelected: true,
                // }));
                // setNameInput(memory.name);
                // addFriendAsync(memory.name, null);
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
  );
}
