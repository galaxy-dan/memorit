'use client';

import { MdOutlineChevronLeft, MdClear } from 'react-icons/md';
import WriteIcon from 'public/icons/write.svg';
import DeleteIcon from 'public/icons/delete.svg';
import Image from 'next/image';
import RedHeart from 'public/icons/red-heart.svg';
import { useState } from 'react';
import ExampleImage from 'public/example.jpg';
import { useQuery } from '@tanstack/react-query';
import { get } from '@/service/api/http';
import HistoryModal from '@/components/friend/HistoryModal';
import History from '@/components/friend/History';

export default function FriendDetailPage() {
  const { data } = useQuery({
    queryKey: ['friend'],
    queryFn: () => get('/friend/search'),
    refetchInterval: 5000,
  });

  return (
    <div className="flex flex-col ">
      <div className="sticky top-0 flex items-center justify-between pt-6 px-6 bg-white z-10">
        <div className="flex items-center">
          <MdOutlineChevronLeft size="50" />
          <p className="text-3xl font-bold">김하연</p>
        </div>
        <div className="flex items-center gap-2">
          <Image src={WriteIcon} alt={'write'} width={'18'} />
          <Image src={DeleteIcon} alt={'delete'} width={'24'} />
        </div>
      </div>
      <div className="flex justify-evenly pt-9">
        <Image src={RedHeart} alt={'red'} width={'73'} />
        <div className="flex flex-col justify-center items-center">
          <p className="text-lg font-bold">직장</p>
          <p className="text-sm">관계</p>
        </div>
        <div className="flex flex-col justify-center items-center">
          <p className="text-lg font-bold">57K</p>
          <p className="text-sm">나눈 기억</p>
        </div>
        <div className="flex flex-col justify-center items-center">
          <p className="text-lg font-bold">100만</p>
          <p className="text-sm">기억의 크기</p>
        </div>
      </div>
      <div className="my-5">
        <div className="flex flex-col border-2 border-gray-300 mx-5 px-6 py-5 rounded-xl gap-2">
          <p className="text-3xl font-bold">Red Heart</p>
          <p className="text-sm font-bold">앞으로 어떤 관계가 될지 기대돼요!</p>
          <meter
            className="w-full"
            min="0"
            max="100"
            low={20}
            high={65}
            optimum={15}
            value={'60'}
          />
        </div>
      </div>
      <History />
    </div>
  );
}
