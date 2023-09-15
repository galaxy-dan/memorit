import React, { useState, useRef } from 'react';
import Image from 'next/image';
import { BiChevronDown } from 'react-icons/bi';
import { BsImage } from 'react-icons/bs';
import { RiImageAddFill } from 'react-icons/ri';
import { MdCancel } from 'react-icons/md';
import { motion } from 'framer-motion';
import {
  containerCss,
  iconCss,
  innerShadow,
  transitionCss,
  transitionCssSlower,
} from '../styles/inputCSS';

type Props = {};

export default function PictureInput({}: Props) {
  const [isOpen, setIsOpen] = useState<boolean>(false);
  const [isTouched, setIsTouched] = useState<boolean>(false);
  const [isButtonTouched, setIsButtonTouched] = useState<boolean>(false);
  const [imageName, setImageName] = useState<string>('');
  const [imageSrc, setImageSrc] = useState<string>('');
  const fileRef = useRef<HTMLInputElement>(null);

  const handleClick = () => {
    fileRef?.current?.click();
  };

  const handleChange = (e: React.ChangeEvent) => {
    const targetFiles = (e.target as HTMLInputElement).files as FileList;
    setImageName(targetFiles[0].name);
    setImageSrc(URL.createObjectURL(targetFiles[0]));
  };

  const handleCancle = () => {
    setImageName('');
    setImageSrc('');
    if (fileRef && fileRef.current) {
      fileRef.current.value = '';
    }
  };

  return (
    <div className="px-2 pb-4 bg-neutral-200">
      <div className="relative">
        <div
          className={`px-3 py-2 border border-white rounded-xl bg-white ${
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
                className={`w-full text-lg truncate ${
                  imageName === '' ? 'text-gray-400' : 'text-black'
                }`}
              >
                {imageName === '' ? '사진' : imageName}
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
          className={`px-3 py-2 border border-white rounded-xl bg-white mt-[0.65rem] mb-10 ${transitionCssSlower()} ${
            !isOpen ? ' grid-rows-[0fr] ' : ' grid-rows-[1fr] '
          } grid transition-[grid-template-rows] relative w-full z-0 mx-0`}
        >
          <div className="overflow-hidden row-span-1 pt-7">
            <div className="pt-3">
              <input
                ref={fileRef}
                type="file"
                accept="image/*"
                className="hidden"
                onChange={handleChange}
              />
              {imageName === '' ? (
                <button
                  className={`w-full h-36 px-4 my-4 ${
                    isButtonTouched
                      ? 'bg-gray-300 shadow-inner ' + innerShadow()
                      : 'bg-gray-200 '
                  } rounded-xl text-center ${transitionCss()} `}
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
                    className="mx-auto max-h-72 rounded-2xl"
                    alt="이미지 선택"
                    src={imageSrc}
                    width={400}
                    height={200}
                    onClick={handleClick}
                  ></Image>
                  <MdCancel
                    className="absolute top-0 right-0 text-2xl bg-white rounded-full p-0"
                    onClick={handleCancle}
                  />
                </div>
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}