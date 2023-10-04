import { addMemoryType, memory, postArticle } from '@/model/memory';
import { get, getConfig, getUrl, post, put } from './http';
import axios from 'axios';
import { historyDetail } from '@/model/history';

export const addMemory = async (
  memory: addMemoryType,
  image: string | null,
) => {
  const res = await axios.post<postArticle>(
    getUrl(`/history`),
    {
      friendId: memory.friendID,
      date: memory.date,
      type: memory.type,
      amount: memory.isMoney ? memory.money : null,
      item: memory.isMoney ? null : memory.present,
      detail: memory.memo,
      image: image,
      given: !memory.isSend,
    },
    getConfig(),
  );
  return res.data.articleId;
};

export const editMemory = async (
  articleId: number,
  memory: addMemoryType,
  image: string | null,
) => {
  const res = await put(`/history/detail/${articleId}`, {
    articleId: articleId,
    friendId: memory.friendID,
    date: memory.date,
    type: memory.type,
    amount: memory.isMoney ? memory.money : null,
    item: memory.isMoney ? null : memory.present,
    detail: memory.memo,
    image: image,
    given: !memory.isSend,
  });
  return res;
};

export const getMemory = async (articleId: number) => {
  const res = await get(`/history/detail/${articleId}`);
  return res;
};
