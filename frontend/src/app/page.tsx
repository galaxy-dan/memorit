'use client';

import History from '@/components/friend/History';
import { useState } from 'react';
import { FaAnglesDown } from 'react-icons/fa6';

export default function Home() {
  const [totalCount, setTotalCount] = useState(0);
  const [totalPeople, setTotalPeople] = useState(0);

  return (
    <div className="snap-y snap-mandatory h-[90vh] overflow-scroll scrollbar-hide">
      <div className="flex flex-col justify-between h-[90vh] p-5">
        <p className="text-xl text-gray-400">Memorit</p>

        <div className="flex flex-col text-4xl font-bold gap-8">
          <p>지금까지</p>
          <div className="text-5xl">
            <p className="inline text-blue-500">{totalPeople}</p>명과{' '}
            <p className="inline text-blue-500">{totalCount}</p>번의
          </div>
          <p>기억을 나눴어요.</p>
        </div>
        <div className="flex flex-col items-center gap-3">
          <div className="text-xl font-medium">전체 히스토리 보기</div>
          <FaAnglesDown size="25" />
        </div>
      </div>
      <History friendId={null} setTotalCount={setTotalCount} setTotalPeople={setTotalPeople} />
    </div>
  );
}
