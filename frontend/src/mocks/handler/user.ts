import { http, HttpResponse } from 'msw';
import { db } from '../db';

export const user = [
    // Get authenticated user info
    http.get('/user', () => {
        const user = db.user.findFirst({
            where: { userId: { equals: '99d7f4dd55244c54a523032169193f40' } }
        });

        if (!user) {
            return new HttpResponse(null, { status: 404 });
        }

        return HttpResponse.json({ user }, { status: 200 });
    }),

    // Register user information
    http.put('/user', async ({ request }) => {
        const body = await request.json() as {
            userId: string;
            nickname: string;
            provider: string;
            receivedCount: number;
            sentCount: number;
            receivedMoney: number;
            sentMoney: number;
            withdrawal: boolean;
        };

        const updatedUser = db.user.update({
            where: { userId: { equals: body.userId } },
            data: body,
        });

        if (!updatedUser) {
            return new HttpResponse(null, { status: 404 });
        }

        return HttpResponse.json({ user: updatedUser }, { status: 200 });
    }),

    // Update nickname
    http.put('/user/update-nickname', async ({ request }) => {
        const body = await request.json() as { userId: string; nickname: string };

        const updatedUser = db.user.update({
            where: { userId: { equals: body.userId } },
            data: { nickname: body.nickname },
        });

        if (!updatedUser) {
            return new HttpResponse(null, { status: 404 });
        }

        return HttpResponse.json({ result: true }, { status: 200 });
    }),

    // Update withdrawal status
    http.put('/user/withdrawal', async ({ request }) => {
        const body = await request.json() as { userId: string; withdrawal: boolean };

        const updatedUser = db.user.update({
            where: { userId: { equals: body.userId } },
            data: { withdrawal: body.withdrawal },
        });

        if (!updatedUser) {
            return new HttpResponse(null, { status: 404 });
        }

        return HttpResponse.json({ result: true }, { status: 200 });
    }),
];