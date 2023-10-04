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
  image: string;
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
};

export type editType = {
  articleId: number;
};

export type memory = {
  articleId: number;
  friendId: string;
  date: string;
  type: string;
  amount: number;
  item: string;
  given: boolean;
  errorMsg: string;
  detail: string;
  image: string;
};
