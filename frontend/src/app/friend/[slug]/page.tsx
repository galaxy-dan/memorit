'use client';

import { MdOutlineChevronLeft, MdClear } from 'react-icons/md';
import WriteIcon from 'public/icons/write.svg';
import DeleteIcon from 'public/icons/delete.svg';
import Image from 'next/image';
import RedHeart from 'public/icons/red-heart.svg';
import { useState } from 'react';
import ExampleImage from 'public/example.jpg';

export default function FriendDetailPage() {
  const [isModal, setIsModal] = useState(false);

  return (
    <>
      <div className="flex flex-col min-h-screen">
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
            <p className="text-sm font-bold">
              앞으로 어떤 관계가 될지 기대돼요!
            </p>
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

        <div className="bg-slate-200 grow rounded-t-xl p-6">
          <div className="flex justify-between pb-2">
            <div className="relative">
              <p className="text-lg font-bold">히스토리</p>
              <div className="absolute w-[66px] h-2 bg-blue-400 opacity-70 bottom-[0.15rem] " />
            </div>
            <p>받은 기억</p>
          </div>
          {[1, 2, 3, 4, 5, 6, 7, 8, 9, 10].map((el) => (
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
      </div>
      {!isModal && (
        <div className="fixed bottom-[105px] bg-orange-400 w-full z-30 rounded-t-xl px-7">
          <div className="flex items-center justify-between my-6">
            <div
              className="flex items-center"
              onClick={() => setIsModal((prev) => !prev)}
            >
              <MdClear size="30" />
            </div>
            <div className="flex items-center gap-2">
              <Image src={WriteIcon} alt={'write'} width={'18'} />
              <Image src={DeleteIcon} alt={'delete'} width={'24'} />
            </div>
          </div>
          <p className="text-xl font-bold">결혼식</p>
          <p className="text-sm font-medium my-6">200,000원</p>
          <div className="relative aspect-video rounded-2xl overflow-hidden">
            <Image
              src={ExampleImage}
              alt="example"
              className="object-cover"
              fill
            />
          </div>
          <p className="text-xs my-3">2023-07-14</p>
          <p className="text-xl font-medium my-5">
            하연이의 결혼식, <br /> 내 결혼식때는 100만원 달라고 해야지
          </p>
        </div>
      )}
    </>
  );
}
