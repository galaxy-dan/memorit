import { addMemoryType, showMenuType, showModalType } from '@/model/memory';
import dayjs from 'dayjs';
import { atom } from 'recoil';

export const addMemoryState = atom<addMemoryType>({
  key: 'addMemory',
  default: {
    isSend: true,
    name: '',
    category: '',
    categoryList: [],
    nameSelected: false,
    categorySelected: false,
    money: 0,
    isMoney: true,
    present: '',
    relation: '',
    memo: '',
    imageFile: null,
    imageSrc: '',
    imageName: '',
    date: dayjs(),
  },
});

export const showMenuState = atom<showMenuType>({
  key: 'showMenu',
  default: {
    showCategoryMenu: false,
    showNameMenu: false,
  },
});

export const showModalState = atom<showModalType>({
  key: 'showModal',
  default: {
    showDateMenu: false,
  },
});
