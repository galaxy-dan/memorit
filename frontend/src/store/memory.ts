import { addMemory } from '@/model/memory';
import { atom } from 'recoil';


export const addMemoryState = atom<addMemory>({
  key: 'addMemory',
  default: {
    isSend: true,
    name: '',
    category: '',
    nameList: [],
    categoryList: [],
    nameSelected: false,
    categorySelected: false,
    money: 0,
    isMoney: true,
    present: '',
    relation: '',
    memo: '',
    image: '',
  },
});
