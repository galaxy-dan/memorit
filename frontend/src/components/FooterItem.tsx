'use client';

import { ReactElement, ReactNode } from 'react';
import { usePathname } from 'next/navigation';
import Link from 'next/link';

type Props = {
  icon: ReactNode;
  clickedIcon: ReactNode;
  path: string;
  text: string;
};

export default function FooterItem({ icon, clickedIcon, path, text }: Props) {
  const pathName = usePathname();

  return (
    <Link
      href={path}
      className="flex flex-col justify-center items-center gap-1 pt-3 pb-4 flex-1"
    >
      {pathName === path ? clickedIcon : icon}
      <div>{text}</div>
    </Link>
  );
}
