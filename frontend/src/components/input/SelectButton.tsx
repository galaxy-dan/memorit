import { motion } from 'framer-motion';
import { useState } from 'react';
import { AiOutlineCheck } from 'react-icons/ai';
import { innerShadow } from './inputCSS';

type Props = {
  isSelected: boolean;
  position: string;
  text: string;
  onClickFunction?: Function;
};

const cssCommon = 'flex justify-center items-center border-stone-400 py-2';

const cssLeft = 'border-r rounded-l-3xl ';
const cssMiddle = 'border-r ';
const cssRight = 'rounded-r-3xl ';

const cssSelected = 'px-2 bg-slate-300';
const cssNotSelected = 'px-4 bg-white ';
const cssTouched = 'px-4 bg-slate-100 ' + innerShadow;

const cssTransition = 'transition ease-in-out duration-[250ms] ';

function getPositionCss(position: string) {
  position = position.toLowerCase();

  if (position === 'l') {
    return cssLeft;
  } else if (position === 'm') {
    return cssMiddle;
  } else if (position === 'r') {
    return cssRight;
  } else {
    throw new Error('position은 l, m, r 중에 하나를 선택해주세요');
  }
}

export default function SelectButton({
  isSelected,
  position,
  text,
  onClickFunction,
}: Props) {
  const [isTouched, setIsTouched] = useState<boolean>(false);
  
  return (
    <>
      <motion.div
        initial={false}
        transition={{ duration: 0.1 }}
        className={
          cssCommon +
          cssTransition +
          `
          ${cssCommon} 
          ${isSelected ? cssSelected : isTouched ? cssTouched : cssNotSelected}
          ${getPositionCss(position)} 
          `
        }
        onClick={() => {
          if (onClickFunction) {
            onClickFunction();
          }
        }}
        onTouchStart={() => {
          setIsTouched(true);
        }}
        onTouchEnd={() => {
          setIsTouched(false);
        }}
      >
        {isSelected && <AiOutlineCheck className="mr-1" />}
        <p>{text}</p>
      </motion.div>
    </>
  );
}
