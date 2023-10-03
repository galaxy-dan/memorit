import dayjs, { Dayjs } from "dayjs";

export function dateToStr(date: Dayjs) { 
    return dayjs(date).format('YYYY-MM-DD');
}

export function dateToStrKR(date: Dayjs) {
    return dayjs(date).locale('ko').format(`YYYY-MM-DD(ddd)`);
}