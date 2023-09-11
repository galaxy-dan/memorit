'use client';


import { IoMdClose } from 'react-icons/io';

export default function AddMemoryPage() {
  

  return (
    <>
      {/* 상단 탭 부분 */}
      <div className="flex justify-between items-center w-full py-5 px-5">
        <IoMdClose className="text-2xl" />
        <p className="text-2xl">기억 더하기</p>
        <button className="text-red-400">저장</button>
      </div>

      
    </>
  );
}
