import { http, HttpResponse } from 'msw';
import { db } from '../db';

const uuid = '99d7f4dd55244c54a523032169193f40';

export const category = [
    // Register category
    http.post('/category', async () => {
        return HttpResponse.json(
            {
                message: 'Category registered successfully'
            },
            { status: 201 }
        );
    }),

    // Search categories
    http.get('/category', () => {
        const categoryList = db.category.findMany({
            where: {
                userId: { equals: uuid }
            }
        });

        return HttpResponse.json(
            {
                categoryList: categoryList
            },
            { status: 200 }
        );
    })
];