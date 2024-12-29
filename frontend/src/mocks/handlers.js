import { category } from './handler/category';
import { friend } from './handler/friend';
import { history } from './handler/history';
import { historyType } from './handler/historyType';
import { user } from './handler/user';

export const handlers = [
  ...friend,
  ...history,
  ...category,
  ...historyType,
  ...user,
];
