import { GoHome, GoHomeFill } from 'react-icons/go';
import { BiPlusCircle } from 'react-icons/bi';
import { MdOutlinePeopleAlt } from 'react-icons/md';

export default function Footer() {
  return (
    <footer className="fixed bottom-0 max-w-md w-full bg-slate-300 flex px-2 items-start gap-2">
      <div className="flex flex-col justify-center items-center gap-1 pt-3 pb-4 flex-1">
        <GoHome size="50" />
        <div>홈</div>
      </div>
      <div className="flex flex-col justify-center items-center gap-1 pt-3 pb-4 flex-1">
        <GoHome size="50" />
        <div>홈</div>
      </div>
      <div className="flex flex-col justify-center items-center gap-1 pt-3 pb-4 flex-1">
        <GoHome size="50" />
        <div>홈</div>
      </div>
    </footer>
  );
}
