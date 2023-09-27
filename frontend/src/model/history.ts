export type history = {
  articleId: number;
  friendId: string;
  date: string;
  type: string;
  amount: number;
  item: string;
  given: boolean;
  errorMsg: string;
};

export type historyDetail = history & { detail: string; image: string };

export type historyList = {
  list: history[];
};
