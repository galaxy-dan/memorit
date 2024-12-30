import { http, HttpResponse } from 'msw';
import { db } from '../db';

export const category = [
  /**
   * @description 카테고리를 등록하는 핸들러
   */
  http.post('/category', async ({ request }) => {
    const body = await request.json() as { categoryName: string };

    // 카테고리 등록
    const newCategory = db.category.create({
      categoryName: body.categoryName,
    });

    return HttpResponse.json(
      {
        message: '카테고리가 성공적으로 등록되었습니다',
        categoryId: newCategory.categoryName,
      },
      { status: 201 },
    );
  }),

  /**
   * @description 카테고리를 검색하는 핸들러
   */
  http.get('/category', () => {
    // 모든 카테고리 가져오기
    const categoryList = db.category.getAll().map((el) => el.categoryName);

    return HttpResponse.json(
      {
        categoryList: categoryList,
      },
      { status: 200 },
    );
  }),
];
