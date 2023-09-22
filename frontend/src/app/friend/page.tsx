'use client';
import { get } from '@/service/api/http';
import { useQuery } from '@tanstack/react-query';
import Image from 'next/image';
import { useRouter } from 'next/navigation';
import RedHeart from 'public/icons/red-heart.svg';
import { MdFilterList } from 'react-icons/md';

const categoryList = [
  '전체',
  '직장',
  '고등학교 친구',
  '친구',
  '가족',
  '동료',
  '직장',
  '고등학교 친구',
  '친구',
  '가족',
  '동료',
];

export default function FriendPage() {
  const router = useRouter();
  const { data } = useQuery({
    queryKey: ['category'],
    queryFn: () => get('/category'),
    refetchInterval: 5000,
  });

  return (
    <div className="flex flex-col  px-4">
      <p className="text-3xl font-bold text-center my-16">친구 목록</p>
      <div className="w-full overflow-auto scrollbar-hide">
        <div className="flex gap-3">
          {categoryList.map((el, index) => (
            <div
              key={el}
              className="bg-red-50 px-6 py-2 rounded-xl shadow-sm whitespace-nowrap"
            >
              {el}
            </div>
          ))}
        </div>
      </div>

      <div className="flex justify-end py-5">
        <div className="flex items-center gap-2">
          <MdFilterList size="20" />
          <p className="font-medium text-sm">이름 순</p>
        </div>
      </div>

      <div className="grid grid-cols-3">
        {[
          1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5,
          6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6,
        ].map((el, index) => (
          <div
            key={index}
            className="border-2 border-green-500 rounded-lg px-2 m-2 shadow-md cursor-pointer"
            onClick={() => router.push(`/friend/${el}`)}
          >
            <div className="flex justify-end items-center gap-1">
              <Image src={RedHeart} alt={'red'} width={'15'} />
              <p className="font-bold">10</p>
            </div>
            <div>
              <p className="font-bold">김철수</p>
              <p className="text-gray-400">친구</p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
