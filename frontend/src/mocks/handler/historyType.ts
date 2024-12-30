import { http, HttpResponse } from 'msw';
import { db } from '../db';

const uuid = '99d7f4dd55244c54a523032169193f40';

export const historyType = [
  // Register history type
  http.post('/type', async ({ request }) => {
    const body = (await request.json()) as { typeName: string };

    const newHistoryType = db.historyType.create({
      typeId: crypto.randomUUID(),
      userId: uuid,
      typeName: body.typeName,
    });

    return HttpResponse.json(
      {
        message: 'History type registered successfully',
        typeId: newHistoryType.typeId,
      },
      { status: 201 },
    );
  }),

  // Search history types
  http.get('/type/search', () => {
    const typeList = db.historyType.getAll().map((el) => el.typeName);

    return HttpResponse.json(
      {
        typeList: typeList,
      },
      { status: 200 },
    );
  }),
];
