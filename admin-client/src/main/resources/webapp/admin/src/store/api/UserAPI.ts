import {UserModel} from "../../model";
import {post,get} from "../HttpClient";

export function loginAPI(user: { username: string, password: string }) {
    return  post<string>("/api-gateway/user-service/user/authenticate",user);
}
export function getUserInfoAPI() {
    return  get<UserModel>("/api-gateway/user-service/user");
}



export default loginAPI;