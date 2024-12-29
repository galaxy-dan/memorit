import { category } from './handler/category';
import { friend } from './handler/friend';
import { history } from './handler/history';

export const handlers = [...friend, ...history, ...category];
