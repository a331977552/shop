import UserModel from "../model/UserModel";
import RestResult from "../model/RestResult";
import axios from "axios";

export function loginAPI(user: { username: string, password: string }) {

    return new Promise<RestResult<string>>((resolve,reject)=>{

        axios.post<RestResult<string>>("/api-gateway/user-service/user/authenticate", user).then(response =>{
            const result = response.data;
            resolve(result);
        }).catch(reason => {
            console.log(reason.response.data);
            reject(reason.response.data)
        })
    });
    // return {
    //     timestamp: new Date(),
    //     code: 400,
    //     msg: 'bad request'
    // };
}

export default loginAPI;