import { http, HttpResponse } from 'msw';

const uuid = '99d7f4dd55244c54a523032169193f40';

export const historyType = [
    // Register history type
    http.post('/type', async ({ request }) => {
        return HttpResponse.json(
            {
                message: 'History type registered successfully',
            },
            { status: 201 }
        );
    }),

    // Search history types
    http.get('/type/search', ({ request }) => {
        // Mock response with sample history types
        return HttpResponse.json(
            {
                typeList: [
                    'Birthday',
                    'Wedding',
                    'Anniversary',
                    'Holiday',
                    'Other'
                ]
            },
            { status: 200 }
        );
    }),
];