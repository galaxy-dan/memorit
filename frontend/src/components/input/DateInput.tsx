import React, { ReactNode, useState } from 'react';
import { containerCss, iconCss, inputCss } from './inputCSS';
import { useRecoilState } from 'recoil';
import { addMemoryState, showModalState } from '@/store/memory';
import { addMemoryType, showModalType } from '@/model/memory';
import { DateCalendar } from '@mui/x-date-pickers/DateCalendar';
import { LocalizationProvider } from '@mui/x-date-pickers';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import dayjs, { Dayjs } from 'dayjs';
import 'dayjs/locale/ko';
import { MdCancel } from 'react-icons/md';
import { dateToStr, dateToStrKR } from '@/service/date';

type Props = {
  placeholder?: string;
  icon?: ReactNode;
  className?: string;
};

export default function DateInput({ placeholder, icon, className }: Props) {
  const [memory, setMemory] = useRecoilState<addMemoryType>(addMemoryState);
  const [showModal, setShowModal] =
    useRecoilState<showModalType>(showModalState);
  const [isTouched, setIsTouched] = useState<boolean>(false);
  const [isCancelButtonTouched, setIsCancelButtonTouched] =
    useState<boolean>(false);
  const [date, setDate] = useState<Dayjs>(dayjs());

  return (
    <>
      <div className="border border-white">
        <div
          className={
            containerCss + ' flex items-center border file:' + className
          }
          onClick={() => {
            setShowModal((prev) => ({ ...prev, showDateMenu: true }));
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
            value={dateToStrKR(date)}
            readOnly={true}
          />
        </div>
      </div>
      {showModal.showDateMenu && (
        <div
          className="w-screen h-full bg-opacity-30 bg-black fixed z-20 top-0 left-0 flex justify-center items-center"
          onClick={(e) => {
            setShowModal((prev) => ({ ...prev, showDateMenu: false }));
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
                value={date}
                onChange={(newValue) => {
                  setDate(newValue || dayjs());
                  setMemory((prev) => ({
                    ...prev,
                    date: dateToStr(newValue || dayjs()),
                  }));
                }}
                views={['year', 'month', 'day']}
              ></DateCalendar>
            </LocalizationProvider>
            <MdCancel
              className={`absolute -top-2 -right-2 text-4xl ${
                isCancelButtonTouched && ' text-slate-300'
              } bg-white rounded-full p-0`}
              onClick={(e: React.MouseEvent<HTMLDivElement, MouseEvent>) => {
                setShowModal((prev) => ({ ...prev, showDateMenu: false }));
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
