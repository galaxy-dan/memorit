export type friend = {
  userId: string;
  friendId: string;
  name: string;
  category: string | null;
  receivedCount: number;
  sentCount: number;
  receivedMoney: number;
  sentMoney: number;
  recentDate: string;
};

export type friendList = {
  list: friend[];
};

export type friendID = {
  friendUUID: string;
}