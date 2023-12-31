import { GoHome, GoHomeFill } from 'react-icons/go';
import { MdAddCircleOutline } from 'react-icons/md';
import { MdOutlinePeopleAlt, MdPeopleAlt } from 'react-icons/md';
import FooterItem from './FooterItem';

const footerList = [
  {
    icon: <GoHome size="40" />,
    clickedIcon: <GoHomeFill size="40" />,
    path: '/',
    text: '홈',
  },
  {
    icon: <MdAddCircleOutline size="40" />,
    clickedIcon: <MdAddCircleOutline size="40" />,
    path: '/add',
    text: '기록추가',
  },
  {
    icon: <MdOutlinePeopleAlt size="40" />,
    clickedIcon: <MdPeopleAlt size="40" />,
    path: '/friend',
    text: '친구',
  },
];

export default function Footer() {
  return (
    <footer className="fixed bottom-0 h-[10vh] max-w-md w-full bg-slate-300 flex px-2 items-start gap-2">
      {footerList.map(({ icon, clickedIcon, path, text }) => (
        <FooterItem
          key={path}
          icon={icon}
          clickedIcon={clickedIcon}
          path={path}
          text={text}
        />
      ))}
    </footer>
  );
}
