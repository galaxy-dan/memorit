import { getRandomItem } from '@/service/utils';
import { http, HttpResponse } from 'msw';

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

    const histories = Array.from({ length: 100 }, (_, index) => ({
      articleId: `history-${index + 1}`,
      given: getRandomItem([true, false]),
      type: getRandomItem(['결혼식', '장례식', '돌잔치', '생일', '기타']),
      amount: getRandomItem([10000, 20000, 30000, 40000, 50000]),
      date: '2021-10-10',
      friendName: getRandomItem(['John Doe', 'Jane Doe', 'Alice', 'Bob']),
    }));

    const paginatedHistories = histories.slice(startIndex, endIndex);

    return HttpResponse.json(
      {
        list: paginatedHistories,
        totalPages: histories.length,
        pageNumber,
        pageSize,
        numOfHistories: histories.length,
        numOfFriends: 10,
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
