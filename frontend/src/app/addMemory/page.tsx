'use client';

import { useState } from 'react';

import PictureInput from '@/components/pictureInput';
import SearchInput from '@/components/searchInput';
import TextInput from '@/components/textInput';
import SelectButton from '@/components/selectButton';
import SelectButtonGroup from '@/components/selectButtonGroup';
import TextareaInput from '@/components/textareaInput';

import { IoMdClose } from 'react-icons/io';
import { BsPeople, BsPerson } from 'react-icons/bs';
import { BiCategory } from 'react-icons/bi';
import { MdOutlineAttachMoney } from 'react-icons/md';
import { AiOutlineGift } from 'react-icons/ai';
import { CgNotes } from 'react-icons/cg';

import { motion } from 'framer-motion';
export default function AddMemoryPage() {
  const [isSend, setIsSend] = useState<boolean>(true);
  const [isMoney, setIsMoney] = useState<boolean>(true);

  function setSendTrue() {
    setIsSend(true);
  }

  function setSendFalse() {
    setIsSend(false);
  }

  return (
    <div className="bg-neutral-200 w-full min-h-screen pb-16">
      {/* 상단 탭 부분 */}
      <div className="flex justify-between items-center w-full py-5 px-5">
        <motion.button
          className="rounded-full p-1"
          initial={false}
          whileTap={{
            backgroundColor: '#D0D0D0',
          }}
          transition={{
            duration: 0.7,
            scale: {
              type: 'spring',
              damping: 5,
              stiffness: 100,
              restDelta: 0.001,
            },
          }}
        >
          <IoMdClose className="text-2xl" />
        </motion.button>
        <p className="text-2xl">기억 더하기</p>
        <motion.button
          className="text-red-400 p-1"
          initial={false}
          whileTap={{
            backgroundColor: '#D0D0D0',
          }}
          transition={{
            duration: 0.7,
            scale: {
              type: 'spring',
              damping: 5,
              stiffness: 100,
              restDelta: 0.001,
            },
          }}
        >
          저장
        </motion.button>
      </div>

      {/* 송수신 선택 부분 */}

      <SelectButtonGroup>
        <SelectButton
          isSelected={isSend}
          position={'l'}
          text={'보낸 기억'}
          onClickFunction={setSendTrue}
        />
        <SelectButton
          isSelected={!isSend}
          position={'r'}
          text={'받은 기억'}
          onClickFunction={setSendFalse}
        />
      </SelectButtonGroup>

      <SearchInput
        type={'category'}
        icon={<BiCategory />}
        placeholder="카테고리"
      />
      <hr className="border border-neutral-300 my-2" />
      <div className="flex w-full pl-2 items-center relative">
        <input
          id="isMoney"
          type="radio"
          className="accent-pink-500 w-5 h-5"
          checked={isMoney}
          onClick={() => {
            setIsMoney(true);
          }}
        ></input>
        <TextInput
          icon={<MdOutlineAttachMoney />}
          placeholder="금액"
          className="w-full"
        />
      </div>
      <label className="flex w-full pl-2 items-center relative">
        <input
          id="isPresent"
          type="radio"
          className="accent-pink-500 w-5 h-5"
          checked={!isMoney}
          onClick={() => {
            setIsMoney(false);
          }}
        ></input>
        <TextInput
          icon={<AiOutlineGift />}
          placeholder="선물"
          className="w-full"
        />
      </label>
      <hr className="border border-neutral-300  my-2" />
      <SearchInput type={'name'} icon={<BsPerson />} placeholder="이름" />
      <TextInput icon={<BsPeople />} placeholder="관계" />
      <hr className="border border-neutral-300  my-2" />
      <TextareaInput icon={<CgNotes />} placeholder="메모" />
      <PictureInput />
    </div>
  );
}
