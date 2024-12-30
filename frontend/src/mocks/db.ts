import { factory, primaryKey, nullable } from '@mswjs/data';

export const db = factory({
  friend: {
    id: primaryKey(String),
    name: String,
    rank: Number,
    category: String,
  },
  history: {
    articleId: primaryKey(String),
    type: String,
    amount: nullable(Number),
    item: nullable(String),
    friendName: String,
    friendId: String,
    date: String,
    detail: String,
    image: String,
    given: Boolean,
  },
  category: {
    categoryName: primaryKey(String),
  },
  historyType: {
    typeId: primaryKey(String),
    userId: String,
    typeName: String,
  },
  user: {
    userId: primaryKey(String),
    nickname: String,
    provider: String,
    receivedCount: Number,
    sentCount: Number,
    receivedMoney: Number,
    sentMoney: Number,
    withdrawal: Boolean,
  },
});

// Seed initial data
Array.from({ length: 50 }, (_, index) => {
  const names = ['John Doe', 'Jane Doe', 'Alice', 'Bob', 'Charlie'];
  const categories = ['가족', '친구', '직장동료', '이웃', '기타'];
  db.friend.create({
    id: crypto.randomUUID(),
    name: names[index % names.length],
    rank: (index % 5) + 1,
    category: categories[index % categories.length],
  });
});

// Create multiple history entries
Array.from({ length: 100 }, (_, index) => {
  db.history.create({
    articleId: crypto.randomUUID(),
    type: ['결혼식', '장례식', '돌잔치', '생일', '기타'][index % 5],
    amount: [10000, 20000, 30000, 40000, 50000][index % 5],
    item: '',
    friendName: ['John Doe', 'Jane Doe', 'Alice', 'Bob'][index % 4],
    friendId: `friend-${(index % 2) + 1}`,
    date: ['2021-01-01', '2021-02-01', '2021-03-01', '2021-04-01'][index % 4],
    detail: `Event ${index + 1}`,
    image: '',
    given: index % 2 === 0,
  });
});

// Create multiple category entries
Array.from({ length: 3 }, (_, index) => {
  const categories = ['가족', '친구', '직장동료'];
  db.category.create({
    categoryName: categories[index],
  });
});

// Create multiple history type entries
Array.from({ length: 5 }, (_, index) => {
  const types = ['결혼식', '장례식', '돌잔치', '생일', '기타'];
  db.historyType.create({
    typeId: crypto.randomUUID(),
    userId: '99d7f4dd55244a523032169193f40',
    typeName: types[index],
  });
});

// Create user data
db.user.create({
  userId: '99d7f4dd55244c54a523032169193f40',
  nickname: '테스트 사용자',
  provider: 'kakao',
  receivedCount: 0,
  sentCount: 0,
  receivedMoney: 0,
  sentMoney: 0,
  withdrawal: false,
});
