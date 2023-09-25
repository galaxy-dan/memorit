'use client';

import SelectButton from '@/components/input/SelectButton';
import SelectButtonGroup from '@/components/input/SelectButtonGroup';
import CategoryInput from '@/components/input/CategoryInput';
import MoneyInput from '@/components/input/MoneyInput';
import PresentInput from '@/components/input/PresentInput';
import NameInput from '@/components/input/NameInput';
import RelationInput from '@/components/input/RelationInput';
import MemoInput from '@/components/input/MemoInput';
import PictureInput from '@/components/input/PictureInput';

import { IoMdClose } from 'react-icons/io';
import { BsCalendarDate, BsPeople, BsPerson } from 'react-icons/bs';
import { BiCategory } from 'react-icons/bi';
import { MdOutlineAttachMoney } from 'react-icons/md';
import { AiOutlineGift } from 'react-icons/ai';
import { CgNotes } from 'react-icons/cg';

import { motion } from 'framer-motion';
import { addMemoryType } from '@/model/memory';
import { useRecoilState, useResetRecoilState } from 'recoil';
import { addMemoryState, showMenuState } from '@/store/memory';
import DateInput from '@/components/input/DateInput';
import { getS3URL, uploadImage } from '@/service/image';

export default function AddMemoryPage() {
  const [memory, setMemory] = useRecoilState<addMemoryType>(addMemoryState);

  const resetShowMenu = useResetRecoilState(showMenuState);

 

  function setSendTrue() {
    setMemory((prev) => ({ ...prev, isSend: true }));
  }

  function setSendFalse() {
    setMemory((prev) => ({ ...prev, isSend: false }));
  }

  function Line() {
    return <hr className="border border-neutral-300 my-2" />;
  }

  const onSubmit = async () => {

    if(memory.imageFile){
      const url = await getS3URL(memory.imageName);
      await uploadImage(url, memory.imageFile);
    }
    
  };

  function onCancle() {}

  return (
    <div
      className="bg-neutral-200 w-full min-h-screen pb-16"
      onClick={resetShowMenu}
    >
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
          onClick={onCancle}
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
          onClick={onSubmit}
        >
          저장
        </motion.button>
      </div>

      {/* 송수신 선택 부분 */}

      <SelectButtonGroup>
        <SelectButton
          isSelected={memory.isSend}
          position={'l'}
          text={'보낸 기억'}
          onClickFunction={setSendTrue}
        />
        <SelectButton
          isSelected={!memory.isSend}
          position={'r'}
          text={'받은 기억'}
          onClickFunction={setSendFalse}
        />
      </SelectButtonGroup>

      <CategoryInput
        type={'category'}
        icon={<BiCategory />}
        placeholder="카테고리"
      />

      <Line />

      <MoneyInput
        icon={<MdOutlineAttachMoney />}
        placeholder="금액"
        className="w-full"
      />
      <PresentInput
        icon={<AiOutlineGift />}
        placeholder="선물"
        className="w-full"
      />
      <Line />
      <NameInput type={'name'} icon={<BsPerson />} placeholder="이름" />
      <RelationInput icon={<BsPeople />} placeholder="관계" />
      <Line />
      <DateInput icon={<BsCalendarDate />} />
      <MemoInput icon={<CgNotes />} placeholder="메모" />
      <PictureInput />
    </div>
  );
}
