'use client';

import { MdClear } from 'react-icons/md';
import WriteIcon from 'public/icons/write.svg';
import DeleteIcon from 'public/icons/delete.svg';
import Image from 'next/image';
import ExampleImage from 'public/images/example.jpg';
import { get } from '@/service/api/http';
import { UseQueryResult, useQuery } from '@tanstack/react-query';
import { history, historyDetail } from '@/model/history';

type Props = {
  isModal: boolean;
  setIsModal: Function;
  articleId: number;
};

export default function HistoryModal({
  isModal,
  setIsModal,
  articleId,
}: Props) {
  const { data: historyData }: UseQueryResult<historyDetail> = useQuery({
    queryKey: ['history', articleId],
    queryFn: () => get(`/history/detail/${articleId}`),
    refetchInterval: 5000,
    enabled: !!articleId,
  });

  return (
    <>
      {isModal && (
        <div className="fixed bottom-[10vh] bg-orange-400 w-screen max-w-md z-30 rounded-t-xl px-7">
          <div className="flex items-center justify-between my-6">
            <div
              className="flex items-center"
              onClick={() => setIsModal((prev: boolean) => !prev)}
            >
              <MdClear size="30" />
            </div>
            <div className="flex items-center gap-2">
              <Image src={WriteIcon} alt={'write'} width={'18'} />
              <Image src={DeleteIcon} alt={'delete'} width={'24'} />
            </div>
          </div>
          <p className="text-xl font-bold">{historyData?.type}</p>
          <p className="text-sm font-medium my-6">{`${historyData?.amount}원`}</p>
          <div className="relative aspect-video rounded-2xl overflow-hidden">
            <Image
              src={historyData?.image ?? ExampleImage}
              alt="example"
              className="object-cover"
              fill
            />
          </div>
          <p className="text-xs my-3">{historyData?.date}</p>
          <p className="text-xl font-medium my-5">{historyData?.detail}</p>
        </div>
      )}
    </>
  );
}
