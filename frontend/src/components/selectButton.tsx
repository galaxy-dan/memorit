import { AiOutlineCheck } from 'react-icons/ai';

type Props = {
  isSelected: boolean;
  position: string;
  text: string;
  selectedBgColor?: string | undefined;
  onClickFunction?: Function;
};

const cssCommon = 'flex justify-center items-center border-stone-400 py-2 ';

const cssLeft = 'border-r rounded-l-3xl ';
const cssMiddle = 'border-r ';
const cssRight = 'rounded-r-3xl ';

const cssSelected = 'px-2 ';
const cssNotSelected = 'px-4 bg-white ';

const cssTransition = 'transition ease-in-out delay-75 ';

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
  selectedBgColor = 'bg-red-200',
  onClickFunction,
}: Props) {
  return (
    <>
      <div
        className={
          cssCommon +
          cssTransition +
          `
          ${cssCommon} 
          ${isSelected ? cssSelected + ' ' + selectedBgColor : cssNotSelected} 
          ${getPositionCss(position)} 
          `
        }
        onClick={() => {
          if (onClickFunction) {
            onClickFunction();
          }
        }}
      >
        {isSelected && <AiOutlineCheck className="mr-1" />}
        <p>{text}</p>
      </div>
    </>
  );
}
