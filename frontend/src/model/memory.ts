import { Dayjs } from 'dayjs';

export type addMemoryType = {
  isSend: boolean;
  name: string;
  type: string;
  nameSelected: boolean;
  typeSelected: boolean;
  money: number;
  isMoney: boolean;
  present: string;
  category: string | undefined;
  memo: string;
  imageFile: File | null;
  imageName: string;
  date: string;
  friendID: string;
};

export type showMenuType = {
  showTypeMenu: boolean;
  showNameMenu: boolean;
};

export type showModalType = {
  showDateMenu: boolean;
};

export type postArticle = {
  articleId: number;
}