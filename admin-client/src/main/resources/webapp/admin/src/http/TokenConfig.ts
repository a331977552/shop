

export function getToken():string{

    const token = window.localStorage.getItem("token") as string;
    return token;
}
export function setToken(token: string):void{
    window.localStorage.setItem("token",token);
}

