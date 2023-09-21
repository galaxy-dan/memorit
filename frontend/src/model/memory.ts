import { Dayjs } from 'dayjs';

export type addMemoryType = {
  isSend: boolean;
  name: string;
  category: string;
  categoryList: string[];
  nameSelected: boolean;
  categorySelected: boolean;
  money: number;
  isMoney: boolean;
  present: string;
  relation: string | undefined;
  memo: string;
  imageSrc: string;
  imageName: string;
  date: Dayjs;
};

export type showMenuType = {
  showCategoryMenu: boolean;
  showNameMenu: boolean;
};

export type showModalType = {
  showDateMenu: boolean;
};
