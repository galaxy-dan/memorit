import { MdOutlineChevronLeft, MdCreate } from 'react-icons/md';
import { FiEdit2, FiTrash2 } from 'react-icons/fi';
import WriteIcon from 'public/icons/write.svg';
import DeleteIcon from 'public/icons/delete.svg';
import Image from 'next/image';

export default function FriendDetailPage() {
  return (
    <div>
      <div className="flex items-center justify-between pt-6 px-6">
        <div className='flex items-center'>
          <MdOutlineChevronLeft size="50" />
          <p className="text-3xl font-bold">김하연</p>
        </div>
        <div className='flex items-center gap-2'>
          <Image src={WriteIcon} alt={'write'} width={'18'} />
          <Image src={DeleteIcon} alt={'delete'} width={'24'} />
        </div>
      </div>
    </div>
  );
}
