'use client';

import SelectButton from '@/components/selectButton';
import SelectButtonGroup from '@/components/selectButtonGroup';
import { useState } from 'react';
import { IoMdClose } from 'react-icons/io';

export default function AddMemoryPage() {
  const [send, setSend] = useState<boolean>(true);

  function setSendTrue() {
    setSend(true);
  }

  function setSendFalse() {
    setSend(false);
  }

  return (
    <>
      {/* 상단 탭 부분 */}
      <div className="flex justify-between items-center w-full py-5 px-5">
        <IoMdClose className="text-2xl" />
        <p className="text-2xl">기억 더하기</p>
        <button className="text-red-400">저장</button>
      </div>

      {/* 송수신 선택 부분 */}
      <SelectButtonGroup>
        <SelectButton
          isSelected={send}
          position={'l'}
          text={'보낸 기억'}
          onClickFunction={setSendTrue}
        />
        <SelectButton
          isSelected={!send}
          position={'r'}
          text={'받은 기억'}
          onClickFunction={setSendFalse}
        />
      </SelectButtonGroup>
    </>
  );
}
