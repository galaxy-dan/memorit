import { get, post } from "./http";


export function getTypeList(){

}

export const getTypeListByName = async(name:string) =>{
    const res = await get(`/friend/search?keyword=${name}`);
    return res;
}

export const addType = async(name:string, type:string|null) => {
    const res = await post(`/friend`,{name:name, type: type});
    return res;
}
