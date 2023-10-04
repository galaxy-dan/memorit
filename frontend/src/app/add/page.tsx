'use client';

import SelectButton from '@/components/input/SelectButton';
import SelectButtonGroup from '@/components/input/SelectButtonGroup';
import TypeInput from '@/components/input/TypeInput';
import MoneyInput from '@/components/input/MoneyInput';
import PresentInput from '@/components/input/PresentInput';
import NameInput from '@/components/input/NameInput';
import CategoryInput from '@/components/input/CategoryInput';
import MemoInput from '@/components/input/MemoInput';
import PictureInput from '@/components/input/PictureInput';

import { IoMdClose } from 'react-icons/io';
import { BsCalendarDate, BsPeople, BsPerson } from 'react-icons/bs';
import { BiCategory } from 'react-icons/bi';
import { MdOutlineAttachMoney } from 'react-icons/md';
import { AiOutlineGift } from 'react-icons/ai';
import { CgNotes } from 'react-icons/cg';
import { addMemoryType } from '@/model/memory';
import { useRecoilState, useResetRecoilState } from 'recoil';
import { addMemoryState, errorState, showMenuState } from '@/store/memory';
import DateInput from '@/components/input/DateInput';

import Button from '@/components/input/Button';

import { getS3URL, uploadImage } from '@/service/image';
import { useState } from 'react';
import { errorType } from '@/model/error';
import { addMemory, editMemory } from '@/service/api/memory';
import { useRouter } from 'next/navigation';
import { inputValid } from '@/service/input';
import useCustomBack from '@/service/useCustomBack';

const Line = () => {
  return <hr className="border-[0.01rem] border-neutral-300 my-1" />;
};

export default function AddMemoryPage() {
  const [memory, setMemory] = useRecoilState<addMemoryType>(addMemoryState);
  const [isSubmitting, setIsSubmitting] = useState<boolean>(false);
  const [error, setError] = useRecoilState<errorType>(errorState);

  const resetShowMenu = useResetRecoilState(showMenuState);
  const resetMemory = useResetRecoilState(addMemoryState);
  const resetError = useResetRecoilState(errorState);

  const router = useRouter();

  function setSendTrue() {
    setMemory((prev) => ({ ...prev, isSend: true }));
  }

  function setSendFalse() {
    setMemory((prev) => ({ ...prev, isSend: false }));
  }

  useCustomBack(() => {
    resetError();
    resetMemory();
    history.back();
  });

  const onSubmit = async () => {
    if (!isSubmitting && CheckError()) {
      try {
        setIsSubmitting(true);

        // 이미지 있을 때
        if (memory.imageFile) {
          // URL을 먼저 받고 백엔드에 관련 파일 올리고
          const url = await getS3URL(memory.imageName);
          // 이후에 이미지를 S3 서버에 전송, 실패하면 백엔드에 실패 및 url 삭제 요청
          const imgUrl = url.split('?')[0];
          const id = await addMemory(memory, imgUrl);
          // 업로드 실패 시
          if (!(await uploadImage(url, memory.imageFile))) {
            await editMemory(id, memory, null);
          }
        }
        // 이미지 없을 때
        else {
          await addMemory(memory, null);
        }
        resetError();
        resetMemory();
        router.push('/');
      } catch {
      } finally {
        setIsSubmitting(false);
      }
    }
  };

  function onCancle() {
    history.back();
  }

  function CheckError() {
    let result = true;
    // 각 함수를 1회씩 무조건 실행해야 함
    result = CheckType() && result;
    result = CheckMoney() && result;
    result = CheckPresent() && result;
    result = CheckName() && result;
    result = CheckMemo() && result;
    return result;
  }

  function CheckType() {
    // 타입 : 필수, 1자 이상 10자 이하
    if (memory.type.length > inputValid.type.maxLength) {
      setError((prev) => ({
        ...prev,
        type: `최대 글자 수는 ${inputValid.type.maxLength}자 입니다.`,
      }));
      return false;
    } else if (
      !memory.typeSelected ||
      memory.type.length < inputValid.type.minLength
    ) {
      setError((prev) => ({ ...prev, type: '타입을 선택해주세요' }));
      return false;
    } else {
      setError((prev) => ({ ...prev, type: '' }));
      return true;
    }
  }

  function CheckMoney() {
    // 금액 : 반필수, 100000000원 이하, 1원 이상
    if (memory.isMoney) {
      if (!memory.money || memory.money < inputValid.money.minSize) {
        setError((prev) => ({ ...prev, money: '금액을 입력해주세요' }));
        return false;
      }
    }
    setError((prev) => ({ ...prev, money: '' }));
    return true;
  }

  function CheckPresent() {
    // 선물 : 반필수, 20자 이하, 1자 이상
    if (!memory.isMoney) {
      if (memory.present.length > inputValid.present.maxLength) {
        setError((prev) => ({
          ...prev,
          present: `최대 글자 수는 ${inputValid.present.maxLength}자 입니다.`,
        }));
        return false;
      } else if (memory.present.length < inputValid.present.minLength) {
        setError((prev) => ({ ...prev, present: '선물을 입력해주세요' }));
        return false;
      }
    }
    setError((prev) => ({ ...prev, present: '' }));
    return true;
  }

  function CheckName() {
    // 이름 : 필수, 1자 이상, 20자 이하
    if (memory.name.length > inputValid.name.maxLength) {
      setError((prev) => ({
        ...prev,
        name: `최대 글자 수는 ${inputValid.name.maxLength}자 입니다.`,
      }));
      return false;
    } else if (
      !memory.nameSelected ||
      memory.name.length < inputValid.name.minLength
    ) {
      setError((prev) => ({ ...prev, name: '친구를 선택해주세요' }));
      return false;
    } else {
      setError((prev) => ({ ...prev, name: '' }));
      return true;
    }
  }

  function CheckMemo() {
    // 메모 : 20자 이하
    if (memory.memo.length > inputValid.memo.maxLength) {
      setError((prev) => ({
        ...prev,
        memo: `최대 글자 수는 ${inputValid.memo.maxLength}자 입니다.`,
      }));
      return false;
    } else {
      setError((prev) => ({ ...prev, memo: '' }));
      return true;
    }
  }

  return (
    <div>
      <div
        className="bg-white border fixed z-10 w-full h-screen overflow-y-auto select-none"
        onClick={resetShowMenu}
      >
        {/* 상단 탭 부분 */}
        <div className="flex justify-between items-center w-full py-5 px-4">
          <Button onClick={() => onCancle} icon={<IoMdClose />} />
          <p className="text-[1.65rem] font-semibold">기억 더하기</p>
          <Button text="저장" onClick={() => onSubmit} />
        </div>

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
        <TypeInput
          type={'type'}
          icon={<BiCategory />}
          placeholder="*경조사 타입"
        />
        <Line />

        <MoneyInput
          icon={<MdOutlineAttachMoney />}
          placeholder={`${memory.isMoney ? '*' : ''}금액`}
          className="w-full"
        />
        <PresentInput
          icon={<AiOutlineGift />}
          placeholder={`${!memory.isMoney ? '*' : ''}선물`}
          className="w-full"
        />
        <Line />
        <NameInput type={'name'} icon={<BsPerson />} placeholder="*이름" />
        <CategoryInput icon={<BsPeople />} placeholder="카테고리" />
        <Line />
        <DateInput icon={<BsCalendarDate />} />
        <MemoInput icon={<CgNotes />} placeholder="메모" />
        <PictureInput />
      </div>
    </div>
  );
}
