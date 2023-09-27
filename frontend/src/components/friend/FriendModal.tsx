import * as yup from 'yup';
import { useForm, SubmitHandler } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import { MdClear } from 'react-icons/md';
import WriteIcon from 'public/icons/write.svg';
import DeleteIcon from 'public/icons/delete.svg';
import Image from 'next/image';
import ExampleImage from 'public/images/example.jpg';
import { BsPeople } from 'react-icons/bs';
import FriendNameInput from './FriendNameInput';
import FriendRelationInput from './FriendRelationInput';
import { useMutation, useQueryClient } from '@tanstack/react-query';
import { post } from '@/service/api/http';
import { BiSolidSave } from 'react-icons/bi';

type Props = {
  isModal: boolean;
  setIsModal: Function;
};

const schema = yup
  .object({
    name: yup.string().required(),
    category: yup.string().required(),
  })
  .required();

type Inputs = {
  name: string;
  category: string;
};

export default function FriendModal({ isModal, setIsModal }: Props) {
  const queryClient = useQueryClient();

  const {
    register,
    handleSubmit,
    watch,
    reset,
    setValue,
    formState: { errors, isSubmitting },
  } = useForm<Inputs>({
    resolver: yupResolver(schema),
  });

  const mutation = useMutation({
    mutationFn: (body: Inputs) => {
      return post(`/friend`, body);
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['friend'] });
      setIsModal(false);
    },
  });

  const onSubmit: SubmitHandler<Inputs> = async (data) => {
    mutation.mutate(data);
    reset();
  };

  return (
    <>
      {isModal && (
        <div className="fixed bottom-[45vh] bg-gray-100 w-screen max-w-md z-30 rounded-xl px-7">
          <form onSubmit={handleSubmit(onSubmit)}>
            <div className="flex items-center justify-between my-6">
              <div
                className="flex items-center"
                onClick={() => setIsModal((prev: boolean) => !prev)}
              >
                <MdClear size="30" />
              </div>
              <div className="flex items-center gap-2">
                <button>
                  <BiSolidSave size={25} />
                </button>
              </div>
            </div>
            <p className="text-2xl font-bold text-center pb-6">친구 추가</p>

            {/* <input type='text' {...register("name")}/> */}
            <FriendNameInput props={{ ...register('name') }} />
            <FriendRelationInput icon={<BsPeople />} placeholder="관계" setValue={setValue} props={{...register("category")}} />
          </form>
        </div>
      )}
    </>
  );
}
