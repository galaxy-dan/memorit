import React, { ReactNode, useState } from 'react';
import { containerCss, iconCss } from '../input/inputCSS';
import { useMemoryStore } from '@/store/memory'; // Zustand store import
import { addMemoryType, showMenuType } from '@/model/memory';
import { motion } from 'framer-motion';
import { Category } from '@/app/friend/page';
import {
  UseQueryResult,
  useMutation,
  useQuery,
  useQueryClient,
} from '@tanstack/react-query';
import { get, post } from '@/service/api/http';

type Props = {
  placeholder?: string;
  icon?: ReactNode;
  className?: string;
  setValue: Function;
  props: any;
  watch: Function;
};

export default function FriendRelationInput({
  placeholder,
  icon,
  className,
  setValue,
  props,
  watch,
}: Props) {
  const { memory, setMemory } = useMemoryStore();
  const [showMenu, setShowMenu] = useState<boolean>(false);
  const queryClient = useQueryClient();

  const { data: categoryData }: UseQueryResult<Category> = useQuery({
    queryKey: ['category'],
    queryFn: () => get('/category'),
  });

  const mutation = useMutation({
    mutationFn: (body: { categoryName: string }) => {
      return post(`/category`, body);
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['category'] });
    },
  });

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
          {!categoryData?.categoryList?.includes(watch('category')) &&
            watch('category') !== '' && (
              <motion.p
                className="text-lg px-5 pt-3 pb-5 truncate rounded-b-xl"
                onClick={() => {
                  mutation.mutate({ categoryName: watch('category') });
                }}
                whileTap={{
                  backgroundColor: '#D0D0D0',
                }}
              >
                추가 : &nbsp;&quot;{watch('category')}&quot;
              </motion.p>
            )}
        </div>
      )}
    </div>
  );
}
