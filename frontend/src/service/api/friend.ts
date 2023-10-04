import axios from 'axios';
import { get, getConfig, getUrl, post } from './http';
import { friendID } from '@/model/friend';

export function getFriendList() {}

export const getFriendListByName = async (name: string) => {
  const res = await get(`/friend/search?keyword=${name}`);
  return res;
};

export const addFriend = async (name: string, category: string | null) => {
  const res = await axios.post<friendID>(
    getUrl(`/friend`),
    { name: name, category: category },
    getConfig(),
  );
  return res.data.friendUUID;
};

export const getFriendByID = async (id: string) => {
  console.log("id: "+ id);
  const res = await get(`/friend/${id}`);
  console.log(res);
  return res;
};