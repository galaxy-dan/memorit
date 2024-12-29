import React, { ReactNode, useState } from 'react';
import { containerCss, iconCss, inputCss } from './inputCSS';
import { DateCalendar } from '@mui/x-date-pickers/DateCalendar';
import { LocalizationProvider } from '@mui/x-date-pickers';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import dayjs from 'dayjs';
import 'dayjs/locale/ko';
import { MdCancel } from 'react-icons/md';
import { dateToStr } from '@/service/date';
import { useMemoryStore } from '@/store/memory';

type Props = {
  placeholder?: string;
  icon?: ReactNode;
  className?: string;
};

export default function DateInput({ placeholder, icon, className }: Props) {
  const { memory, setMemory } = useMemoryStore();
  const { showModal, setShowModal } = useMemoryStore();
  const [isTouched, setIsTouched] = useState<boolean>(false);
  const [isCancelButtonTouched, setIsCancelButtonTouched] =
    useState<boolean>(false);

  return (
    <>
      <div className="border border-white">
        <div
          className={
            containerCss + ' flex items-center border file:' + className
          }
          onClick={() => {
            setShowModal({ ...showModal, showDateMenu: true });
          }}
          onTouchStart={() => {
            setIsTouched(true);
          }}
          onTouchEnd={() => {
            setIsTouched(false);
          }}
        >
          <div className={`${iconCss(isTouched, false)}`}>{icon}</div>
          <input
            type="text"
            className={inputCss}
            placeholder={placeholder}
            value={memory.date}
            readOnly={true}
          />
        </div>
      </div>
      {showModal.showDateMenu && (
        <div
          className="w-screen h-full bg-opacity-30 bg-black fixed z-20 top-0 left-0 flex justify-center items-center"
          onClick={(e) => {
            setShowModal({ ...showModal, showDateMenu: false });
            e.stopPropagation();
          }}
        >
          <div
            className="h-fit bg-white rounded-xl relative"
            onClick={(e: React.MouseEvent<HTMLDivElement, MouseEvent>) =>
              e.stopPropagation()
            }
          >
            <LocalizationProvider dateAdapter={AdapterDayjs} adapterLocale="ko">
              <DateCalendar
                value={dayjs(memory.date)}
                onChange={(newValue) => {
                  setMemory({
                    ...memory,
                    date: dateToStr(newValue || dayjs()),
                  });
                }}
                views={['year', 'month', 'day']}
              ></DateCalendar>
            </LocalizationProvider>
            <MdCancel
              className={`absolute -top-2 -right-2 text-4xl ${
                isCancelButtonTouched && ' text-slate-300'
              } bg-white rounded-full p-0`}
              onClick={(e: React.MouseEvent<SVGElement, MouseEvent>) => {
                setShowModal({ ...showModal, showDateMenu: false });
                e.stopPropagation();
              }}
              onTouchStart={() => {
                setIsCancelButtonTouched(true);
              }}
              onTouchEnd={() => {
                setIsCancelButtonTouched(false);
              }}
            />
          </div>
        </div>
      )}
    </>
  );
}
