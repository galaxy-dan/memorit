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
import { addMemoryType, editType, memory } from '@/model/memory';
import DateInput from '@/components/input/DateInput';
import Button from '@/components/input/Button';

import { getS3URL, uploadImage } from '@/service/image';
import { useEffect, useState } from 'react';
import { errorType } from '@/model/error';
import { addMemory, editMemory, getMemory } from '@/service/api/memory';
import {
  useRouter,
  usePathname,
  useParams,
  useSearchParams,
} from 'next/navigation';
import { inputValid } from '@/service/input';
import useCustomBack from '@/service/useCustomBack';
import { UseQueryResult, useQuery, useQueryClient } from '@tanstack/react-query';
import TypeInputNoEdit from '@/components/input/TypeInputEdit';
import NameInputEdit from '@/components/input/NameInputEdit';
import NameCateInputNoEdit from '@/components/input/NameInputEdit';
import { useMemoryStore } from '@/store/memory';

const Line = () => {
  return <hr className="border-[0.01rem] border-neutral-300 my-1" />;
};

export default function AddMemoryPage() {
  const { memory, setMemory, resetMemory, error, setError, resetError, resetShowMenu, edit } = useMemoryStore();
  const [isSubmitting, setIsSubmitting] = useState<boolean>(false);

  const router = useRouter();
  const queryClient = useQueryClient();
  
  const { data: categoryData }: UseQueryResult<memory> = useQuery({
    queryKey: ['history', edit.articleId],
    queryFn: () => getMemory(edit.articleId),
  });

  useEffect(() => {
    if (edit.articleId === 0) {
      history.back();
    }
    if (categoryData) {
      setMemory({
        ...memory,
        type: categoryData?.type,
        money: categoryData?.amount || 0,
        present: categoryData?.item || '',
        date: categoryData?.date || '',
        memo: categoryData?.detail || '',
        isSend: !categoryData?.given,
        isMoney: categoryData?.amount !== null,
        imageName:
          categoryData.image !== null ? categoryData.image.split('/')[3] : '',
        image: categoryData.image,
        friendID: categoryData?.friendId,
      });
    }
  }, [categoryData, setMemory, edit.articleId]);

  function setSendTrue() {
    setMemory({ ...memory, isSend: true });
  }

  function setSendFalse() {
    setMemory({ ...memory, isSend: false });
  }

  useCustomBack(() => {
    history.back();
    resetError();
    resetMemory();
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
          await editMemory(edit.articleId, memory, imgUrl);
          // 업로드 실패 시
          if (!(await uploadImage(url, memory.imageFile))) {
            await editMemory(edit.articleId, memory, null);
          }
        }
        // 이미지 없을 때
        else {
          await editMemory(edit.articleId, memory, memory.image);
        }
        queryClient.invalidateQueries({ queryKey: ['historyList', edit.articleId, memory.friendID] });
        queryClient.invalidateQueries({ queryKey: ['historyList', memory.friendID] });
        queryClient.invalidateQueries({ queryKey: ['friend'] });
        router.push('/');
        resetError();
        resetMemory();
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
    result = CheckMoney() && result;
    result = CheckPresent() && result;
    result = CheckMemo() && result;
    return result;
  }

  function CheckMoney() {
    // 금액 : 반필수, 100000000원 이하, 1원 이상
    if (memory.isMoney) {
      if (!memory.money || memory.money < inputValid.money.minSize) {
        setError({ ...error, money: '금액을 입력해주세요' });
        return false;
      }
    }
    setError({ ...error, money: '' });
    return true;
  }

  function CheckPresent() {
    // 선물 : 반필수, 20자 이하, 1자 이상
    if (!memory.isMoney) {
      if (memory.present.length > inputValid.present.maxLength) {
        setError({
          ...error,
          present: `최대 글자 수는 ${inputValid.present.maxLength}자 입니다.`,
        });
        return false;
      } else if (memory.present.length < inputValid.present.minLength) {
        setError({ ...error, present: '선물을 입력해주세요' });
        return false;
      }
    }
    setError({ ...error, present: '' });
    return true;
  }

  function CheckMemo() {
    // 메모 : 20자 이하
    if (memory.memo.length > inputValid.memo.maxLength) {
      setError({
        ...error,
        memo: `최대 글자 수는 ${inputValid.memo.maxLength}자 입니다.`,
      });
      return false;
    } else {
      setError({ ...error, memo: '' });
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
          <p className="text-[1.65rem] font-semibold">기억 수정하기</p>
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
        <TypeInputNoEdit
          type={'type '}
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
        <NameCateInputNoEdit icon1={<BsPerson />} icon2={<BsPeople />} />
        <Line />
        <DateInput icon={<BsCalendarDate />} />
        <MemoInput icon={<CgNotes />} placeholder="메모" />
        <PictureInput />
      </div>
    </div>
  );
}
