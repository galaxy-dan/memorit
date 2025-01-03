import { useMemoryStore } from '@/store/memory';
import { motion } from 'framer-motion';
import Image from 'next/image';
import ExampleImage from 'public/images/example.png';
import { useRef, useState } from 'react';
import { BiChevronDown } from 'react-icons/bi';
import { BsImage } from 'react-icons/bs';
import { MdCancel } from 'react-icons/md';
import { RiImageAddFill } from 'react-icons/ri';
import {
  containerMX,
  iconCss,
  innerShadow,
  inputCss,
  transitionCss,
  transitionCssSlower,
} from './inputCSS';

type Props = {};

export default function PictureInput({}: Props) {
  const [isOpen, setIsOpen] = useState<boolean>(false);
  const [isTouched, setIsTouched] = useState<boolean>(false);
  const [isButtonTouched, setIsButtonTouched] = useState<boolean>(false);
  const [isCancelButtonTouched, setIsCancelButtonTouched] =
    useState<boolean>(false);
  const { memory, setMemory } = useMemoryStore();
  const [imageSrc, setImageSrc] = useState<string>('');

  const fileRef = useRef<HTMLInputElement>(null);

  const handleClick = () => {
    fileRef?.current?.click();
  };

  const handleChange = async (e: any) => {
    const targetFiles = (e.target as HTMLInputElement).files as FileList;

    if (targetFiles[0]) {
      setMemory({
        ...memory,
        imageName: targetFiles[0].name,
        imageFile: e.target.files[0],
      });
    }
    setImageSrc(URL.createObjectURL(targetFiles[0]));
  };

  const handleCancle = () => {
    setMemory({
      ...memory,
      imageName: '',
      imageFile: null,
    });
    setImageSrc('');
    if (fileRef && fileRef.current) {
      fileRef.current.value = '';
    }
  };

  return (
    <div className={`${containerMX} mt-1 bg-white border-2 rounded-xl `}>
      <div className="relative">
        <div
          className={`px-3 pb-2 border border-white rounded-xl bg-white ${
            isTouched ? ' bg-gray-100 ' : ' bg-white '
          } z-10 absolute w-full  }`}
        >
          <div
            className="w-full flex justify-between items-center relative"
            onClick={() => setIsOpen((prev) => !prev)}
            onTouchStart={() => {
              setIsTouched(true);
            }}
            onTouchEnd={() => {
              setIsTouched(false);
            }}
          >
            <div className={'w-5/6 pr-2 flex '}>
              <div className={iconCss(isOpen, isTouched)}>
                <BsImage />
              </div>
              <p
                className={`${inputCss} truncate ${
                  memory.imageName === '' ? 'text-gray-400' : 'text-black'
                }`}
              >
                {memory.imageName === '' ? '사진' : memory.imageName}
              </p>
            </div>
            <motion.button
              className="text-3xl"
              initial={false}
              animate={{
                scale: isTouched ? 0.95 : 1,
                rotate: isOpen ? 180 : 0,
              }}
              transition={{
                duration: 0.15,
                scale: {
                  type: 'spring',
                  damping: 5,
                  stiffness: 100,
                  restDelta: 0.001,
                },
              }}
            >
              <BiChevronDown />
            </motion.button>
          </div>
        </div>
        <div
          className={`px-3 py-1 border-2 border-white rounded-xl bg-white mt-1 ${transitionCssSlower} ${
            !isOpen ? ' grid-rows-[0fr] ' : ' grid-rows-[1fr] '
          } grid transition-[grid-template-rows] relative w-full z-0 mx-0`}
        >
          <div className="overflow-hidden row-span-1 pt-7">
            <input
              ref={fileRef}
              type="file"
              accept="image/*"
              className="hidden"
              onChange={handleChange}
            />
            {memory.imageName === '' ? (
              <button
                className={`w-full h-36 px-4 my-4 ${
                  isButtonTouched
                    ? 'bg-slate-300 shadow-inner ' + innerShadow
                    : 'bg-slate-200 '
                } rounded-xl text-center ${transitionCss} `}
                onClick={handleClick}
                onTouchStart={() => {
                  setIsButtonTouched(true);
                }}
                onTouchEnd={() => {
                  setIsButtonTouched(false);
                }}
              >
                <RiImageAddFill className="mx-auto text-4xl" />
                <p>이미지추가</p>
              </button>
            ) : (
              <div className="px-2 my-4 relative ">
                <Image
                  className={`mx-auto max-h-72 rounded-2xl shadow-sm`}
                  alt="이미지 선택"
                  src={imageSrc || memory.image || ExampleImage}
                  width={400}
                  height={200}
                  onClick={handleClick}
                />
                <MdCancel
                  className={`absolute top-3 right-5 text-4xl ${
                    isCancelButtonTouched && ' text-slate-200'
                  } bg-white rounded-full p-0`}
                  onClick={handleCancle}
                  onTouchStart={() => {
                    setIsCancelButtonTouched(true);
                  }}
                  onTouchEnd={() => {
                    setIsCancelButtonTouched(false);
                  }}
                />
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}
