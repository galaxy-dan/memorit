import { get, post } from "./http";


export function getFriendList(){

}

export const getFriendListByName = async(name:string) =>{
    const res = await get(`/friend/search?keyword=${name}`);
    return res;
}

export const addFriend = async(name:string, category:string|null) => {
    const res = await post(`/friend`,{name:name, category: category});
    return res;
}
