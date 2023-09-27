import { get, post } from "./http";


export function getTypeList(){

}

export const getTypeListByName = async(type:string) =>{
    const res = await get(`/type/search?keyword=${type}`);
    return res;
}

export const addType = async(type:string) => {
    const res = await post(`/type`,{typeName:type});
    return res;
}
