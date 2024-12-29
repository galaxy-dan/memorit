'use client';

import { MdClear } from 'react-icons/md';
import WriteIcon from 'public/icons/write.svg';
import DeleteIcon from 'public/icons/delete.svg';
import Image from 'next/image';
import ExampleImage from 'public/images/example.png';
import { get } from '@/service/api/http';
import {
  UseQueryResult,
  useMutation,
  useQuery,
  useQueryClient,
} from '@tanstack/react-query';
import { history, historyDetail } from '@/model/history';
import { useRouter } from 'next/navigation';
import Link from 'next/link';
import { editType } from '@/model/memory';
import { useMemoryStore } from '@/store/memory';
import { deleteMemory } from '@/service/api/memory';

type Props = {
  isModal: boolean;
  setIsModal: Function;
  articleId: number;
  friendId: string;
};

export default function HistoryModal({
  isModal,
  setIsModal,
  articleId,
  friendId,
}: Props) {
  const { data: historyData }: UseQueryResult<historyDetail> = useQuery({
    queryKey: ['historyList', articleId, friendId],
    queryFn: () => get(`/history/detail/${articleId}`),
    refetchInterval: 5000,
    enabled: !!articleId,
  });

  const setEdit = useMemoryStore((state) => state.setEdit);

  const router = useRouter();
  const queryClient = useQueryClient();

  const cancel = useMutation({
    mutationFn: () => deleteMemory(articleId),
    onSuccess: () => {
      setIsModal(false);
      queryClient.invalidateQueries({ queryKey: ['historyList', friendId] });
      queryClient.invalidateQueries({ queryKey: ['friend'] });
    },
  });

  return (
    <>
      {isModal && (
        <div className="fixed bottom-[10vh] bg-slate-100 w-screen max-w-md z-30 rounded-t-xl px-7">
          <div className="flex items-center justify-between my-6">
            <div
              className="flex items-center"
              onClick={() => setIsModal((prev: boolean) => !prev)}
            >
              <MdClear size="30" />
            </div>
            <div className="flex items-center gap-2">
              <Image
                src={WriteIcon}
                alt={'write'}
                width={'18'}
                onClick={() => {
                  setEdit({ articleId: articleId });
                  router.push(`/edit`);
                }}
              />
              <Image
                src={DeleteIcon}
                alt={'delete'}
                width={'24'}
                onClick={() => cancel.mutate()}
              />
            </div>
          </div>
          <div className="flex justify-between">
            <p className="text-xl font-bold">{historyData?.type}</p>
            <p className="text-xl font-bold text-gray-400">
              {historyData?.friendName}
            </p>
          </div>
          <p className="text-sm font-medium my-6">
            {historyData?.amount
              ? `${historyData?.amount}원`
              : historyData?.item}
          </p>
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
