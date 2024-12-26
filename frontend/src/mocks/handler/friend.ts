import { http, HttpResponse } from 'msw';

const uuid = '99d7f4dd55244c54a523032169193f40';

export const friend = [
  // Register friend manually
  http.post('/friend', (req) => {
    return HttpResponse.json(
      {
        message: 'Friend registered successfully',
        friendId: 'sample-friend-id',
      },
      { status: 201 },
    );
  }),

  // Register friends from address
  http.post('/friend/select', (req) => {
    return HttpResponse.json({ statusCode: 201 }, { status: 201 });
  }),

  // Get friends list
  http.get('/friend', (req) => {
    return HttpResponse.json(
      {
        friends: [
          { id: 'friend-1', name: 'John Doe', rank: 1 },
          { id: 'friend-2', name: 'Jane Doe', rank: 2 },
        ],
      },
      { status: 200 },
    );
  }),

  // Get friend info
  http.get('/friend/:friendId', (req) => {
    const { friendId } = req.params;
    return HttpResponse.json(
      { id: friendId, name: 'John Doe', rank: 1 },
      { status: 200 },
    );
  }),

  // Update friend info
  http.patch('/friend/:friendId', (req) => {
    const { friendId } = req.params;
    return HttpResponse.json(
      { message: `Friend ${friendId} updated successfully` },
      { status: 200 },
    );
  }),

  // Delete friend by ID
  http.delete('/friend/:friendId', (req) => {
    const { friendId } = req.params;
    return HttpResponse.json(
      { message: `Friend ${friendId} deleted successfully` },
      { status: 200 },
    );
  }),

  // Delete multiple friends
  http.delete('/friend/multiple', (req) => {
    return HttpResponse.json(
      { message: 'Multiple friends deleted successfully' },
      { status: 200 },
    );
  }),

  // Get friends rank
  http.get('/friend/rank', (req) => {
    return HttpResponse.json(
      {
        rank: [
          { id: 'friend-1', rank: 1 },
          { id: 'friend-2', rank: 2 },
        ],
      },
      { status: 200 },
    );
  }),

  // Search friends
  http.get('/friend/search', (req) => {
    return HttpResponse.json(
      {
        results: [
          { id: 'friend-3', name: 'Alice', rank: 3 },
          { id: 'friend-4', name: 'Bob', rank: 4 },
        ],
      },
      { status: 200 },
    );
  }),
];
