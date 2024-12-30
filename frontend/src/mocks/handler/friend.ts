import { category } from './category';
import { http, HttpResponse } from 'msw';
import { db } from '../db';


export const friend = [
  // Register friend manually
  http.post('/friend', async ({ request }) => {
    const body = (await request.json()) as {
      name: string;
      rank: number;
      category: string;
    };

    const newFriend = db.friend.create({
      id: crypto.randomUUID(),
      name: body.name,
      rank: body.rank,
      category: body.category,
    });

    return HttpResponse.json(
      {
        message: '친구가 성공적으로 등록되었습니다',
        friendId: newFriend.id,
      },
      { status: 201 },
    );
  }),

  // Register friends from address
  http.post('/friend/select', async ({ request }) => {
    const body = (await request.json()) as Array<{
      name: string;
      rank: number;
      category: string;
    }>;

    body.forEach((friend) => {
      db.friend.create({
        id: crypto.randomUUID(),
        name: friend.name,
        rank: friend.rank,
        category: friend.category,
      });
    });

    return HttpResponse.json({ statusCode: 201 }, { status: 201 });
  }),

  // Get friends list
  http.get('/friend', () => {
    const friends = db.friend.getAll();
    return HttpResponse.json({ list: friends }, { status: 200 });
  }),

  // Delete multiple friends
  http.delete('/friend/multiple', async ({ request }) => {
    const body = (await request.json()) as { friendIds: string[] };

    body.friendIds.forEach((id) => {
      db.friend.delete({ where: { id: { equals: id } } });
    });

    return HttpResponse.json(
      { message: '선택한 친구들이 삭제되었습니다' },
      { status: 200 },
    );
  }),

  // Get friends rank
  http.get('/friend/rank', () => {
    const friends = db.friend.getAll();
    const rankedFriends = friends.map((f) => ({
      id: f.id,
      rank: f.rank,
    }));

    return HttpResponse.json({ rank: rankedFriends }, { status: 200 });
  }),

  // Search friends
  http.get('/friend/search', ({ request }) => {
    const url = new URL(request.url);
    const category = url.searchParams.get('category') || '';

    const friends = category
      ? db.friend.findMany({
          where: {
            category: {
              equals: category,
            },
          },
        })
      : db.friend.getAll();

    return HttpResponse.json({ list: friends }, { status: 200 });
  }),

  // Get friend info
  http.get('/friend/:friendId', ({ params }) => {
    const friendId = String(params.friendId);
    const friend = db.friend.findFirst({
      where: { id: { equals: friendId } },
    });

    if (!friend) {
      return new HttpResponse(null, { status: 404 });
    }

    return HttpResponse.json(friend, { status: 200 });
  }),

  // Update friend info
  http.patch('/friend/:friendId', async ({ params, request }) => {
    const friendId = String(params.friendId);
    const body = (await request.json()) as Partial<{
      name: string;
      rank: number;
      category: string;
    }>;

    const updatedFriend = db.friend.update({
      where: { id: { equals: friendId } },
      data: body,
    });

    if (!updatedFriend) {
      return new HttpResponse(null, { status: 404 });
    }

    return HttpResponse.json(
      { message: '친구 정보가 업데이트되었습니다' },
      { status: 200 },
    );
  }),

  // Delete friend by ID
  http.delete('/friend/:friendId', ({ params }) => {
    const friendId = String(params.friendId);

    db.friend.delete({
      where: { id: { equals: friendId } },
    });

    return HttpResponse.json(
      { message: '친구가 삭제되었습니다' },
      { status: 200 },
    );
  }),
];
