import React, { ReactNode, useEffect, useState } from 'react';
import { containerCss, iconCss, inputCss } from './inputCSS';
import { useRecoilState } from 'recoil';
import { addMemoryType, showMenuType } from '@/model/memory';
import { addMemoryState } from '@/store/memory';
import { friend } from '@/model/friend';
import { UseQueryResult, useQuery } from '@tanstack/react-query';
import { getFriendByID } from '@/service/api/friend';

type Props = {
  icon1?: ReactNode;
  icon2?: ReactNode;
};

export default function NameCateInputNoEdit({ icon1, icon2 }: Props) {
  const [memory, setMemory] = useRecoilState<addMemoryType>(addMemoryState);

  const { data: friend }: UseQueryResult<friend> = useQuery({
    queryKey: ['friendDetail', memory.friendID],
    queryFn: () => getFriendByID(memory.friendID),
  });

  return (
    <div>
      <div className="border border-white">
        <div className={containerCss + ' flex items-center border relative'}>
          <div className={`${iconCss(false, false)}`}>{icon1}</div>
          <p className={`${inputCss} w-full`}>{friend?.name}</p>
        </div>
      </div>
      <div className="border border-white">
        <div className={containerCss + ' flex items-center border relative'}>
          <div className={`${iconCss(false, false)}`}>{icon2}</div>
          <p className={`${inputCss} w-full`}>{friend?.category || '미지정'}</p>
        </div>
      </div>
    </div>
  );
}
