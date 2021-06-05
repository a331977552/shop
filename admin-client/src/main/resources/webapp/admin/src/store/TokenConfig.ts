


export function getTokenFromStorage():string|null{
    return window.localStorage.getItem("token");
}
export function setTokenToStorage(token: string):void {
    window.localStorage.setItem("token", token);
}
export function removeTokenFromStorage(){
    window.localStorage.removeItem("token")
}

