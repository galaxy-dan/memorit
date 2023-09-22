'use client';

import { useState } from 'react';
import HistoryModal from './HistoryModal';

export default function History() {
  const [isModal, setIsModal] = useState(false);

  return (
    <>
      <div className="bg-slate-200 grow rounded-t-xl p-6 snap-start">
        <div className="flex justify-between pb-2">
          <div className="relative">
            <p className="text-lg font-bold">히스토리</p>
            <div className="absolute w-[66px] h-2 bg-blue-400 opacity-70 bottom-[0.15rem] " />
          </div>
          <p>받은 기억</p>
        </div>
        {[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15].map((el) => (
          <div
            key={el}
            onClick={() => setIsModal((prev) => !prev)}
            className={`flex flex-col ${
              el % 2 === 1 ? 'items-start' : 'items-end'
            } my-1`}
          >
            <div className="flex flex-col border-2 shadow-md w-64 bg-white rounded-xl text-sm font-bold p-3">
              <p>결혼식</p>
              <p>100,000원</p>
            </div>
            <p className="text-xs font-medium ml-3 mt-">2023-07-13</p>
          </div>
        ))}
      </div>
      <HistoryModal isModal={isModal} setIsModal={setIsModal} />
    </>
  );
}
