'use client';

import { MdOutlineChevronLeft, MdClear } from 'react-icons/md';
import WriteIcon from 'public/icons/write.svg';
import DeleteIcon from 'public/icons/delete.svg';
import Image from 'next/image';
import RedHeart from 'public/icons/red-heart.svg';
import OrangeHeart from 'public/icons/orange-heart.svg';
import YellowHeart from 'public/icons/yellow-heart.svg';
import GreenHeart from 'public/icons/green-heart.svg';
import BlueHeart from 'public/icons/blue-heart.svg';
import NavyHeart from 'public/icons/navy-heart.svg';
import PurpleHeart from 'public/icons/purple-heart.svg';

import { useState } from 'react';
import ExampleImage from 'public/example.jpg';
import {
  UseQueryResult,
  useMutation,
  useQuery,
  useQueryClient,
} from '@tanstack/react-query';
import { del, get } from '@/service/api/http';
import HistoryModal from '@/components/friend/HistoryModal';
import History from '@/components/friend/History';
import { usePathname, useRouter, useSearchParams } from 'next/navigation';
import { friend } from '@/model/friend';

const getIcon = (heart: string, alt: string) => {
  return <Image src={heart} alt={alt} width={'73'} />;
};

const gradeList = [
  {
    icon: getIcon(RedHeart, 'red'),
    title: 'Red Heart',
    detail: '앞으로 어떤 관계가 될지 기대돼요!',
  },
  {
    icon: getIcon(OrangeHeart, 'orange'),
    title: 'Orange Heart',
    detail: '관계를 더 쌓아서 친해져봐요!',
  },
  {
    icon: getIcon(YellowHeart, 'yellow'),
    title: 'Yellow Heart',
    detail: '점점 친구가 되어가는 중이에요!',
  },
  {
    icon: getIcon(GreenHeart, 'green'),
    title: 'Green Heart',
    detail: '마음속 친구목록에 추가! 우정을 더 쌓아봐요!',
  },
  {
    icon: getIcon(BlueHeart, 'blue'),
    title: 'Blue Heart',
    detail: '흔들림에도 변치않는 우정!',
  },
  {
    icon: getIcon(NavyHeart, 'navy'),
    title: 'Navy Heart',
    detail: '이정도까지 친한 사람은 손에 꼽아요!',
  },
  {
    icon: getIcon(PurpleHeart, 'purple'),
    title: 'Purple Heart',
    detail: '친구를 넘어선 가족이에요!',
  },
];

const getGrade = (send = 0, receive = 0) => {
  const total = send + receive;
  return Math.floor(total / 2) > 6 ? 6 : Math.floor(total / 2);
};

const getGradeExp = (send = 0, receive = 0) => {
  const total = (send + receive) % 2;

  return (100 / 2) * total;
};

export default function FriendDetailPage() {
  const pathNames = usePathname();
  const router = useRouter();
  const queryClient = useQueryClient();

  const { data: friendData }: UseQueryResult<friend> = useQuery({
    queryKey: ['friend', pathNames.split('/')[2]],
    queryFn: () => get(`/friend/${pathNames.split('/')[2]}`),
    refetchInterval: 5000,
  });

  const mutation = useMutation({
    mutationFn: () => {
      return del(`/friend/${pathNames.split('/')[2]}`);
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['friend'] });
      router.replace('/friend');
    },
  });

  const gradeIndex = getGrade(friendData?.sentCount, friendData?.receivedCount);

  return (
    <div className="flex flex-col grow h-[90vh]">
      <div className="sticky top-0 flex items-center justify-between pt-6 pr-6 bg-white z-10">
        <div className="flex items-center">
          <div onClick={() => router.push('/friend')}>
            <MdOutlineChevronLeft size="40" />
          </div>
          <p className="text-2xl font-bold">{friendData?.name}</p>
        </div>
        <div className="flex items-center gap-2">
          <Image src={WriteIcon} alt={'write'} width={'18'} />
          <Image
            src={DeleteIcon}
            alt={'delete'}
            width={'24'}
            onClick={() => mutation.mutate()}
          />
        </div>
      </div>
      <div className="flex justify-evenly pt-9">
        {gradeList[gradeIndex].icon}
        <div className="flex flex-col justify-center items-center">
          <p className="text-lg font-bold">{friendData?.category ?? '없음'}</p>
          <p className="text-sm">관계</p>
        </div>
        <div className="flex flex-col justify-center items-center">
          <p className="text-lg font-bold">
            {(friendData?.sentCount ?? 0) + (friendData?.receivedCount ?? 0)}
          </p>
          <p className="text-sm">나눈 기억</p>
        </div>
        <div className="flex flex-col justify-center items-center">
          <p className="text-lg font-bold">
            {(friendData?.sentMoney ?? 0) + (friendData?.receivedMoney ?? 0)}
          </p>
          <p className="text-sm">기억의 크기</p>
        </div>
      </div>
      <div className="my-5">
        <div className="flex flex-col border-2 border-gray-300 mx-5 px-6 py-5 rounded-xl gap-2">
          <p className="text-3xl font-bold">{gradeList[gradeIndex].title}</p>
          <p className="text-sm font-bold">{gradeList[gradeIndex].detail}</p>
          <meter
            className="w-full"
            min="0"
            max="100"
            low={20}
            high={65}
            optimum={15}
            value={getGradeExp(
              friendData?.sentCount,
              friendData?.receivedCount,
            )}
          />
        </div>
      </div>
      <History friendId={pathNames.split('/')[2]} />
    </div>
  );
}
