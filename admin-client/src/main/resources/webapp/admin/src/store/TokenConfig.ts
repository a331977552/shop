import {loadItem, removeItem, saveItem} from "../services";


export function getTokenFromStorage():string|null{
    return loadItem('token');
}
export function setTokenToStorage(token: string):void {
    saveItem('token',token);
}
export function removeTokenFromStorage(){
    removeItem('token');
}

