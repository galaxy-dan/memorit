import History from '@/components/friend/History';
import Image from 'next/image';

export default function Home() {
  return (
    <>
      <div className="flex flex-col justify-between h-[90vh] p-5">
        <p className="text-xl text-gray-400">Memorit</p>

        <div className="flex flex-col text-3xl font-bold gap-5">
          <p>김싸피님은</p>
          <div>
            <p className="inline text-blue-500">123</p>명과{' '}
            <p className="inline text-blue-500">79</p>번의
          </div>
          <p>기억을 나눴어요.</p>
        </div>
        <div className="text-xl font-medium text-center">
          전체 히스토리 보기
        </div>
      </div>
      <History />
    </>
  );
}
