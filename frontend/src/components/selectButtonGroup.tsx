import { ReactNode } from 'react';

type Props = {
  children: ReactNode;
};

export default function SelectButtonGroup({ children }: Props) {
  return (
    <>
      <div className="w-full flex justify-center text-[0.825rem] mt-3">
        <div className="flex border border-stone-500 rounded-3xl ">
          {children}
        </div>
      </div>
    </>
  );
}