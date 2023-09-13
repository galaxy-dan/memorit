import { GoHome, GoHomeFill } from 'react-icons/go';
import { MdAddCircleOutline } from 'react-icons/md';
import { MdOutlinePeopleAlt, MdPeopleAlt } from 'react-icons/md';
import FooterItem from './FooterItem';

const footerList = [
  {
    icon: <GoHome size="50" />,
    clickedIcon: <GoHomeFill size="50" />,
    path: '/',
    text: '홈',
  },
  {
    icon: <MdAddCircleOutline size="50" />,
    clickedIcon: <MdAddCircleOutline size="50" />,
    path: '/add',
    text: '기록추가',
  },
  {
    icon: <MdOutlinePeopleAlt size="50" />,
    clickedIcon: <MdPeopleAlt size="50" />,
    path: '/friend',
    text: '친구',
  },
];

export default function Footer() {
  return (
    <footer className="sticky bottom-0 max-w-md w-full bg-slate-300 flex px-2 items-start gap-2">
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
