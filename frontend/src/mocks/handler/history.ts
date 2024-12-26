import { http, HttpResponse } from 'msw';

const uuid = "99d7f4dd55244c54a523032169193f40";

export const history = [
  // Create history
  http.post('/history', (req) => {
    return HttpResponse.json(
      { message: 'History created successfully', historyId: 'sample-history-id' },
      { status: 201 }
    );
  }),

  // Get history detail
  http.get('/history/detail/:articleId', (req) => {
    const { articleId } = req.params;
    return HttpResponse.json(
      { id: articleId, title: 'Sample History', content: 'This is a sample history content.' },
      { status: 200 }
    );
  }),

  // Get total history
  http.get('/history/all', (req) => {
    return HttpResponse.json(
      {
        histories: [
          { id: 'history-1', title: 'History 1', content: 'Content 1' },
          { id: 'history-2', title: 'History 2', content: 'Content 2' }
        ]
      },
      { status: 200 }
    );
  }),

  // Update history
  http.put('/history/detail/:articleId', (req) => {
    const { articleId } = req.params;
    return HttpResponse.json(
      { message: `History ${articleId} updated successfully` },
      { status: 200 }
    );
  }),

  // Delete history
  http.delete('/history/detail/:articleId', (req) => {
    const { articleId } = req.params;
    return HttpResponse.json(
      { message: `History ${articleId} deleted successfully` },
      { status: 200 }
    );
  })
];