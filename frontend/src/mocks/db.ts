import { factory, primaryKey } from '@mswjs/data';

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
    amount: Number,
    item: String,
    friendName: String,
    friendId: String,
    date: String,
    detail: String,
    image: String,
    given: Boolean,
  },
});

// Seed initial data
db.friend.create({
  id: 'friend-1',
  name: 'John Doe',
  rank: 1,
  category: '가족',
});

db.friend.create({
  id: 'friend-2',
  name: 'Jane Doe',
  rank: 2,
  category: '친구',
});

// Create multiple history entries
Array.from({ length: 100 }, (_, index) => {
  db.history.create({
    articleId: `history-${index + 1}`,
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
