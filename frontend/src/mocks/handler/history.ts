import { http, HttpResponse } from 'msw';
import { db } from '../db';


export const history = [
  // Create history
  http.post('/history', async ({ request }) => {
    const body = await request.json() as {
      type: string;
      amount: number;
      item: string;
      friendName: string;
      friendId: string;
      date: string;
      detail: string;
      image: string;
      given: boolean;
    };

    const newHistory = db.history.create({
      articleId: crypto.randomUUID(),
      ...body
    });

    return HttpResponse.json(
      {
        message: '히스토리가 성공적으로 생성되었습니다',
        historyId: newHistory.articleId,
      },
      { status: 201 },
    );
  }),

  // Get history detail
  http.get('/history/detail/:articleId', ({ params }) => {
    const articleId = String(params.articleId);
    const history = db.history.findFirst({
      where: { articleId: { equals: articleId } }
    });

    if (!history) {
      return new HttpResponse(null, { status: 404 });
    }

    return HttpResponse.json(history, { status: 200 });
  }),

  // Get total history with pagination
  http.get('/history/all', ({ request }) => {
    const url = new URL(request.url);
    const pageNumber = parseInt(url.searchParams.get('pageNumber') || '1', 10);
    const pageSize = 10;
    const startIndex = (pageNumber - 1) * pageSize;

    const allHistories = db.history.findMany({
      take: pageSize,
      skip: startIndex,
    });

    const totalHistories = db.history.getAll().length;
    const uniqueFriends = new Set(db.history.getAll().map((h) => h.friendId))
      .size;

    return HttpResponse.json(
      {
        list: allHistories,
        totalPages: Math.ceil(totalHistories / pageSize),
        pageNumber,
        pageSize,
        numOfHistories: totalHistories,
        numOfFriends: uniqueFriends,
      },
      { status: 200 },
    );
  }),

  // Update history
  http.put('/history/detail/:articleId', async ({ params, request }) => {
    const articleId = String(params.articleId);
    const body = await request.json() as Partial<{
      type: string;
      amount: number;
      item: string;
      friendName: string;
      friendId: string;
      date: string;
      detail: string;
      image: string;
      given: boolean;
    }>;

    const updatedHistory = db.history.update({
      where: { articleId: { equals: articleId } },
      data: body
    });

    if (!updatedHistory) {
      return new HttpResponse(null, { status: 404 });
    }

    return HttpResponse.json(
      { message: '히스토리가 성공적으로 수정되었습니다' },
      { status: 200 },
    );
  }),

  // Delete history
  http.delete('/history/detail/:articleId', ({ params }) => {
    const articleId = String(params.articleId);
    
    db.history.delete({
      where: { articleId: { equals: articleId } }
    });

    return HttpResponse.json(
      { message: '히스토리가 성공적으로 삭제되었습니다' },
      { status: 200 },
    );
  }),
];
