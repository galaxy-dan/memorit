import { getRandomItem } from '@/service/utils';
import { http, HttpResponse } from 'msw';
import { db } from '../db';

const uuid = '99d7f4dd55244c54a523032169193f40';

export const history = [
  // Create history
  http.post('/history', (req) => {
    return HttpResponse.json(
      {
        message: 'History created successfully',
        historyId: 'sample-history-id',
      },
      { status: 201 },
    );
  }),

  // Get history detail
  http.get('/history/detail/:articleId', (req) => {
    const { articleId } = req.params;
    return HttpResponse.json(
      {
        id: articleId,
        title: 'Sample History',
        content: 'This is a sample history content.',
      },
      { status: 200 },
    );
  }),

  // Get total history
  http.get('/history/all', ({ request }) => {
    const url = new URL(request.url);
    const pageNumber = parseInt(url.searchParams.get('pageNumber') || '1', 10);
    const pageSize = 10;
    const startIndex = (pageNumber - 1) * pageSize;
    const endIndex = startIndex + pageSize;

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
  http.put('/history/detail/:articleId', (req) => {
    const { articleId } = req.params;
    return HttpResponse.json(
      { message: `History ${articleId} updated successfully` },
      { status: 200 },
    );
  }),

  // Delete history
  http.delete('/history/detail/:articleId', (req) => {
    const { articleId } = req.params;
    return HttpResponse.json(
      { message: `History ${articleId} deleted successfully` },
      { status: 200 },
    );
  }),
];
