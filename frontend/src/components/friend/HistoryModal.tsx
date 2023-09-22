import { MdClear } from 'react-icons/md';
import WriteIcon from 'public/icons/write.svg';
import DeleteIcon from 'public/icons/delete.svg';
import Image from 'next/image';
import ExampleImage from 'public/example.jpg';

type Props = {
  isModal: boolean;
  setIsModal: Function;
};

export default function HistoryModal({ isModal, setIsModal }: Props) {
  return (
    <>
      {isModal && (
        <div className="fixed bottom-[10vh] bg-orange-400 w-screen max-w-md z-30 rounded-t-xl px-7">
          <div className="flex items-center justify-between my-6">
            <div
              className="flex items-center"
              onClick={() => setIsModal((prev: boolean) => !prev)}
            >
              <MdClear size="30" />
            </div>
            <div className="flex items-center gap-2">
              <Image src={WriteIcon} alt={'write'} width={'18'} />
              <Image src={DeleteIcon} alt={'delete'} width={'24'} />
            </div>
          </div>
          <p className="text-xl font-bold">결혼식</p>
          <p className="text-sm font-medium my-6">200,000원</p>
          <div className="relative aspect-video rounded-2xl overflow-hidden">
            <Image
              src={ExampleImage}
              alt="example"
              className="object-cover"
              fill
            />
          </div>
          <p className="text-xs my-3">2023-07-14</p>
          <p className="text-xl font-medium my-5">
            하연이의 결혼식, <br /> 내 결혼식때는 100만원 달라고 해야지
          </p>
        </div>
      )}
    </>
  );
}
