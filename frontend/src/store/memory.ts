import { errorType } from '@/model/error';
import { addMemoryType, showMenuType, showModalType } from '@/model/memory';
import { dateToStr } from '@/service/date';
import dayjs from 'dayjs';
import { atom } from 'recoil';

export const addMemoryState = atom<addMemoryType>({
  key: 'addMemory',
  default: {
    isSend: true,
    name: '',
    friendID: '',
    type: '',
    nameSelected: false,
    typeSelected: false,
    money: 0,
    isMoney: true,
    present: '',
    category: '',
    memo: '',
    imageName:'',
    imageFile: null,
    date: dateToStr(dayjs()),
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

export const errorState = atom<errorType>({
  key: 'error',
  default:{
    name : '',
    type : '',
    category : '',
    money : '',
    present : '',
    memo : '',
  }
})
