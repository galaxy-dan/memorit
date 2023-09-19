'use client';
import { get } from '@/service/api/http';
import { useQuery } from '@tanstack/react-query';
import Image from 'next/image';
import RedHeart from 'public/icons/red-heart.svg';

const categoryList = ['전체', '직장', '고등학교 친구', '친구'];

export default function FriendPage() {
  const { data } = useQuery({
    queryKey: ['category'],
    queryFn: () => get('/category'),
    refetchInterval: 5000,
  });

  return (
    <div>
      <p className='text-3xl font-bold text-center my-16'>친구 목록</p>
      <div className="">
        <div className="flex gap-3">
          {categoryList.map((el, index) => (
            <div key={el} className="bg-red-50 px-6 py-2 rounded-xl shadow-sm">
              {el}
            </div>
          ))}
        </div>
      </div>

      <div className='flex justify-end'>
        <p>이름 순</p>
      </div>

      <div className="grid grid-cols-3">
        {[1, 2, 3, 4, 5, 6].map((el) => (
          <div key={el} className="border-2 border-green-500 rounded-lg px-2 m-2 shadow-md">
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
