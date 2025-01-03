import { motion } from 'framer-motion';
import { MouseEventHandler, ReactNode } from 'react';

type Props = {
  onClick: MouseEventHandler<HTMLButtonElement>;
  text?: string;
  icon?: ReactNode;
};

export default function Button({ onClick, text, icon }: Props) {
  return (
    <motion.button
      className="rounded-full p-2"
      initial={false}
      whileTap={{
        backgroundColor: '#D0D0D0',
      }}
      transition={{
        duration: 0.7,
        scale: {
          type: 'spring',
          damping: 5,
          stiffness: 100,
          restDelta: 0.001,
        },
      }}
      onClick={onClick}
    >
      <div className="text-3xl px-1">{icon}</div>
      <div className="text-xl font-semibold text-blue-500">{text}</div>
    </motion.button>
  );
}
