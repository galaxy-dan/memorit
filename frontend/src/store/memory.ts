import { addMemoryType, showMenuType, showModalType } from '@/model/memory';
import dayjs from 'dayjs';
import { atom } from 'recoil';

export const addMemoryState = atom<addMemoryType>({
  key: 'addMemory',
  default: {
    isSend: true,
    name: '',
    type: '',
    typeList: [],
    nameSelected: false,
    typeSelected: false,
    money: 0,
    isMoney: true,
    present: '',
    category: '',
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
    showTypeMenu: false,
    showNameMenu: false,
  },
});

export const showModalState = atom<showModalType>({
  key: 'showModal',
  default: {
    showDateMenu: false,
  },
});
