'use client';
import Search from '@/components/friend/Search';
import { friendList } from '@/model/friend';
import { get } from '@/service/api/http';
import {
  UseQueryResult,
  useQuery,
  useQueryClient,
} from '@tanstack/react-query';
import Image from 'next/image';
import { useRouter } from 'next/navigation';
import RedHeart from 'public/icons/red-heart.svg';
import { useState } from 'react';
import { MdFilterList } from 'react-icons/md';

const sortList = [
  {
    title: '등록',
    sort: 'recentRegister',
  },
  {
    title: '받음',
    sort: 'receivedCount',
  },
  {
    title: '보냄',
    sort: 'sentCount',
  },
];

type Category = {
  categoryList: string[];
};

export default function FriendPage() {
  const router = useRouter();
  const [selectedCategory, setSelectedCategory] = useState<string | null>(null);
  const [selectedSortIndex, setSelectedSortIndex] = useState<number>(0);
  const [keyword, setKeyword] = useState<string | null>(null);
  const queryClient = useQueryClient();

  const { data: categoryData }: UseQueryResult<Category> = useQuery({
    queryKey: ['category'],
    queryFn: () => get('/category'),
    refetchInterval: 5000,
  });

  const { data: friendData }: UseQueryResult<friendList> = useQuery({
    queryKey: [
      'friend',
      keyword,
      selectedCategory,
      sortList[selectedSortIndex].sort,
    ],
    queryFn: () =>
      get(`/friend/search`, {
        keyword: keyword,
        category: selectedCategory,
        dataSize: 20,
        pageNumber: 1,
        sortBy: sortList[selectedSortIndex].sort,
      }),
    refetchInterval: 5000,
  });

  return (
    <div className="flex flex-col  px-4">
      <p className="text-3xl font-bold text-center my-16">친구 목록</p>
      <div className="flex justify-end py-6">
        <Search setKeyword={setKeyword} />
      </div>
      <div className="w-full overflow-auto scrollbar-hide">
        <div className="flex gap-3">
          <div
            key={'all'}
            className={`${
              !selectedCategory ? 'bg-red-300' : 'bg-red-50'
            } px-6 py-2 rounded-xl shadow-sm whitespace-nowrap`}
            onClick={() => setSelectedCategory(null)}
          >
            전체
          </div>

          {categoryData?.categoryList?.map((el, index) => (
            <div
              key={el}
              className={`${
                selectedCategory === el ? 'bg-red-300' : 'bg-red-50'
              } px-6 py-2 rounded-xl shadow-sm whitespace-nowrap`}
              onClick={() => setSelectedCategory(el)}
            >
              {el}
            </div>
          ))}
        </div>
      </div>

      <div className="flex justify-end py-5">
        <div
          className="flex items-center gap-2"
          onClick={() =>
            setSelectedSortIndex((prev) =>
              prev + 1 >= sortList.length ? 0 : prev + 1,
            )
          }
        >
          <MdFilterList size="20" />
          <p className="font-medium text-sm">
            {sortList[selectedSortIndex].title}
          </p>
        </div>
      </div>

      <div className="grid grid-cols-3">
        {friendData?.list.length === 0 ? (
          <p>친구가 없습니다</p>
        ) : (
          friendData?.list?.map((el, index) => (
            <div
              key={index}
              className="border-2 border-green-500 rounded-lg px-2 m-2 shadow-md cursor-pointer"
              onClick={() => router.push(`/friend/${el.friendId}`)}
            >
              <div className="flex justify-end items-center gap-1">
                <Image src={RedHeart} alt={'red'} width={'15'} />
                <p className="font-bold">{el.receivedCount + el.sentCount}</p>
              </div>
              <div>
                <p className="font-bold">{el.name}</p>
                <p className="text-gray-400">{el.category}</p>
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
}
