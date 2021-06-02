import UserModel from "../model/UserModel";
import RestResult from "../model/RestResult";
import axios from "axios";

export function loginAPI(user: { username: string, password: string }) {
    return new Promise<RestResult<UserModel>>((resolve, reject) => {
        axios.post<RestResult<UserModel>>("/api-gateway/user-service/user/authenticate", user).then(response => {
            resolve(response.data);
            console.log(response, response.data);
        }).catch(reason => {
            reject(reason.response);
            console.log(reason.response.data);
        });
    });
    // return {
    //     timestamp: new Date(),
    //     code: 400,
    //     msg: 'bad request'
    // };
}

export default loginAPI;