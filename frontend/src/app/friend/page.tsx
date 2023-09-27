'use client';
import Search from '@/components/friend/Search';
import { friend, friendList } from '@/model/friend';
import { get } from '@/service/api/http';
import {
  UseQueryResult,
  useInfiniteQuery,
  useQuery,
  useQueryClient,
} from '@tanstack/react-query';
import Image from 'next/image';
import { useRouter } from 'next/navigation';
import RedHeart from 'public/icons/red-heart.svg';
import { useEffect, useState } from 'react';
import { MdFilterList } from 'react-icons/md';
import { useInView } from 'react-intersection-observer';
import { HiOutlineUserAdd } from 'react-icons/hi';
import FriendModal from '@/components/friend/FriendModal';

const sortList = [
  {
    title: '등록',
    sort: 'recentRegister',
  },
  {
    title: '받음',
    sort: 'receivedCount',
  },
  {
    title: '보냄',
    sort: 'sentCount',
  },
];

export type Category = {
  categoryList: string[];
};

export default function FriendPage() {
  const [isModal, setIsModal] = useState<boolean>(false);
  const router = useRouter();
  const [selectedCategory, setSelectedCategory] = useState<string | null>(null);
  const [selectedSortIndex, setSelectedSortIndex] = useState<number>(0);
  const [keyword, setKeyword] = useState<string | null>(null);
  const queryClient = useQueryClient();
  const { ref, inView } = useInView();

  const { data: categoryData }: UseQueryResult<Category> = useQuery({
    queryKey: ['category'],
    queryFn: () => get('/category'),
    refetchInterval: 5000,
  });

  // const { data: friendData }: UseQueryResult<friendList> = useQuery({
  //   queryKey: [
  //     'friend',
  //     keyword,
  //     selectedCategory,
  //     sortList[selectedSortIndex].sort,
  //   ],
  //   queryFn: () =>
  //     get(`/friend/search`, {
  //       keyword: keyword,
  //       category: selectedCategory,
  //       dataSize: 20,
  //       pageNumber: 1,
  //       sortBy: sortList[selectedSortIndex].sort,
  //     }),
  //   refetchInterval: 5000,
  // });

  const getFriendList = async (query = {}, page = 1) => {
    const res: any = await get(`/friend/search`, query);

    return {
      data: res.list,
      nextPage: page + 1 < res.totalPages ? page + 1 : null,
    };
  };

  const {
    data: friendData,
    fetchNextPage,
    hasNextPage,
    isFetching,
    isFetchingNextPage,
    refetch,
  } = useInfiniteQuery({
    queryKey: [
      'friend',
      keyword,
      selectedCategory,
      sortList[selectedSortIndex].sort,
    ],
    queryFn: ({ pageParam = 1 }) =>
      getFriendList(
        {
          keyword: keyword,
          category: selectedCategory,
          dataSize: 30,
          pageNumber: pageParam,
          sortBy: sortList[selectedSortIndex].sort,
        },
        pageParam,
      ),
    getNextPageParam: (lastPage) => lastPage.nextPage,
  });

  useEffect(() => {
    if (inView && hasNextPage) {
      fetchNextPage();
    }
  }, [fetchNextPage, hasNextPage, inView]);

  return (
    <>
      <div className="flex flex-col h-[90vh] px-4">
        <p className="text-3xl font-bold text-center my-16">친구 목록</p>
        <div className="flex justify-end py-6 gap-2">
          <Search setKeyword={setKeyword} />
          <div onClick={() => setIsModal((prev) => !prev)}>
            <HiOutlineUserAdd size={25} />
          </div>
        </div>
        <div className="w-full overflow-auto scrollbar-hide">
          <div className="flex gap-3">
            <div
              key={'all'}
              className={`${
                !selectedCategory ? 'bg-red-300' : 'bg-red-50'
              } px-6 py-2 rounded-xl shadow-sm whitespace-nowrap`}
              onClick={() => setSelectedCategory(null)}
            >
              전체
            </div>

            {categoryData?.categoryList?.map((el, index) => (
              <div
                key={el}
                className={`${
                  selectedCategory === el ? 'bg-red-300' : 'bg-red-50'
                } px-6 py-2 rounded-xl shadow-sm whitespace-nowrap`}
                onClick={() => setSelectedCategory(el)}
              >
                {el}
              </div>
            ))}
          </div>
        </div>

        <div className="flex justify-end py-5">
          <div
            className="flex items-center gap-2"
            onClick={() =>
              setSelectedSortIndex((prev) =>
                prev + 1 >= sortList.length ? 0 : prev + 1,
              )
            }
          >
            <MdFilterList size="20" />
            <p className="font-medium text-sm">
              {sortList[selectedSortIndex].title}
            </p>
          </div>
        </div>

        <div>
          {friendData?.pages.map((group, i) => (
            <div key={i} className="grid grid-cols-3">
              {group?.data?.map((el: friend, index: number) => (
                <div
                  key={index}
                  className="border-2 border-green-500 rounded-lg px-2 m-2 shadow-md cursor-pointer"
                  onClick={() => router.push(`/friend/${el.friendId}`)}
                >
                  <div className="flex justify-end items-center gap-1">
                    <Image src={RedHeart} alt={'red'} width={'15'} />
                    <p className="font-bold">
                      {el.receivedCount + el.sentCount}
                    </p>
                  </div>
                  <div>
                    <p className="font-bold overflow-ellipsis overflow-hidden whitespace-nowrap">
                      {el.name}
                    </p>
                    <p className="text-gray-400">{el.category ?? '없음'}</p>
                  </div>
                </div>
              ))}
            </div>
          ))}
        </div>
        <div className="flex justify-center">
          <button
            ref={ref}
            onClick={() => fetchNextPage()}
            disabled={!hasNextPage || isFetchingNextPage}
            className="text-sm font-semibold leading-6 text-gray-900"
          >
            {/* {isFetchingNextPage
            ? 'Loading more...'
            : hasNextPage
            ? 'Load Newer'
            : 'Nothing more to load'} */}
          </button>
        </div>
        <div className="flex justify-center text-sm font-semibold leading-6 text-gray-900">
          {/* {isFetching && !isFetchingNextPage ? 'Background Updating...' : null} */}
        </div>
      </div>
      <FriendModal isModal={isModal} setIsModal={setIsModal} />
    </>
  );
}
