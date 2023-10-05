import * as yup from 'yup';
import { useForm, SubmitHandler } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import { MdClear } from 'react-icons/md';
import WriteIcon from 'public/icons/write.svg';
import DeleteIcon from 'public/icons/delete.svg';
import Image from 'next/image';
import ExampleImage from 'public/images/example.png';
import { BsPeople } from 'react-icons/bs';
import FriendNameInput from './FriendNameInput';
import FriendRelationInput from './FriendRelationInput';
import { useMutation, useQueryClient } from '@tanstack/react-query';
import { patch, post } from '@/service/api/http';
import { BiSolidSave } from 'react-icons/bi';
import { friend } from '@/model/friend';
import { useEffect } from 'react';

type Props = {
  isModal: boolean;
  setIsModal: Function;
  isUpdate?: boolean;
  friendData?: friend | undefined;
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

const initFriendData = {
  userId: '',
  friendId: '',
  name: '',
  category: null,
  receivedCount: 0,
  sentCount: 0,
  receivedMoney: 0,
  sentMoney: 0,
  recentDate: '',
};

export default function FriendModal({
  isModal,
  setIsModal,
  isUpdate = false,
  friendData = initFriendData,
}: Props) {
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
      return isUpdate
        ? patch(`/friend/${friendData.friendId}`, body)
        : post(`/friend`, body);
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['friend'] });
      setIsModal(false);
    },
  });

  const onSubmit: SubmitHandler<Inputs> = async (data) => {
    mutation.mutate(data);
    reset({
      name: friendData.name,
      category: friendData.category ?? '없음',
    });
  };

  useEffect(() => {
    reset({
      name: friendData.name,
      category: friendData.category ?? '없음',
    });
  }, [friendData.category, friendData.name, reset]);

  return (
    <>
      {isModal && (
        <div className="fixed bottom-[35vh] bg-gray-100 w-screen max-w-md z-30 rounded-xl px-7 pb-10">
          <form onSubmit={handleSubmit(onSubmit)}>
            <div className="flex items-center justify-between my-6">
              <div
                className="flex items-center"
                onClick={() => setIsModal(false)}
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
            <FriendRelationInput
              icon={<BsPeople />}
              placeholder="관계"
              setValue={setValue}
              props={{ ...register('category') }}
              watch={watch}
            />
          </form>
        </div>
      )}
    </>
  );
}
