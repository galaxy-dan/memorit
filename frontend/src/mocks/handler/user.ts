import { http, HttpResponse } from 'msw';

export const user = [
    // Get authenticated user info
    http.get('/user', () => {
        return HttpResponse.json(
            {
                user: {
                    userId: 'sample-user-id',
                    nickname: 'Sample User',
                    provider: 'KAKAO',
                    receivedCount: 10,
                    sentCount: 5,
                    receivedMoney: 100000,
                    sentMoney: 50000,
                    withdrawal: false,
                },
            },
            { status: 200 },
        );
    }),

    // Register user information
    http.put('/user', () => {
        return HttpResponse.json(
            {
                user: {
                    userId: 'sample-user-id',
                    nickname: 'Updated User',
                    provider: 'KAKAO',
                    receivedCount: 10,
                    sentCount: 5,
                    receivedMoney: 100000,
                    sentMoney: 50000,
                    withdrawal: false,
                },
            },
            { status: 200 },
        );
    }),

    // Update nickname
    http.put('/user/update-nickname', () => {
        return HttpResponse.json(
            {
                result: true,
            },
            { status: 200 },
        );
    }),

    // Update withdrawal status
    http.put('/user/withdrawal', () => {
        return HttpResponse.json(
            {
                result: true,
            },
            { status: 200 },
        );
    }),
];