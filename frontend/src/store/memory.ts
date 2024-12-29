import { errorType } from '@/model/error';
import {
  addMemoryType,
  editType,
  showMenuType,
  showModalType,
} from '@/model/memory';
import { dateToStr } from '@/service/date';
import dayjs from 'dayjs';
import { create } from 'zustand';

interface MemoryState {
  memory: addMemoryType;
  setMemory: (memory: addMemoryType) => void;
  resetMemory: () => void;
  error: errorType;
  setError: (error: errorType) => void;
  resetError: () => void;
  showMenu: showMenuType;
  setShowMenu: (showMenu: showMenuType) => void;
  resetShowMenu: () => void;
  showModal: showModalType;
  setShowModal: (showModal: showModalType) => void;
  resetShowModal: () => void;
  edit: editType;
  setEdit: (edit: editType) => void;
  resetEdit: () => void;
}

export const useMemoryStore = create<MemoryState>((set) => ({
  memory: {
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
    imageName: '',
    imageFile: null,
    image: '',
    date: dateToStr(dayjs()),
  },
  setMemory: (memory) => set({ memory }),
  resetMemory: () =>
    set({
      memory: {
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
        imageName: '',
        imageFile: null,
        image: '',
        date: dateToStr(dayjs()),
      },
    }),
  error: {
    name: '',
    type: '',
    category: '',
    money: '',
    present: '',
    memo: '',
  },
  setError: (error) => set({ error }),
  resetError: () =>
    set({
      error: {
        name: '',
        type: '',
        category: '',
        money: '',
        present: '',
        memo: '',
      },
    }),
  showMenu: {
    showTypeMenu: false,
    showNameMenu: false,
  },
  setShowMenu: (showMenu) => set({ showMenu }),
  resetShowMenu: () =>
    set({
      showMenu: {
        showTypeMenu: false,
        showNameMenu: false,
      },
    }),
  showModal: {
    showDateMenu: false,
  },
  setShowModal: (showModal) => set({ showModal }),
  resetShowModal: () =>
    set({
      showModal: {
        showDateMenu: false,
      },
    }),
  edit: {
    articleId: 0,
  },
  setEdit: (edit) => set({ edit }),
  resetEdit: () => 
    set({
      edit: {
        articleId: 0,
      },
    }),
}));
