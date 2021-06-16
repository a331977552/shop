// Add a request interceptor
import axios from "axios";
import {getTokenFromStorage} from "./TokenConfig";


export function authenticationRequestInterceptor() {
    axios.interceptors.request.use(function (config) {
        // Do something before request is sent
        let token = getTokenFromStorage();
        if (token) {
            config.headers["Authorization"] = "Bearer " + token
        }
        return config;
    }, function (error) {
        // Do something with request error
        return Promise.reject(error);
    });
}

export function addResponseTransformInterceptor() {

    axios.interceptors.response.use((response) => {
        return response.data;
    }, function (reason) {
        console.log("http error:", reason.toJSON());
        if (reason.response?.data) {
            return Promise.reject(reason.response.data)
        } else if (reason.request) {
            return Promise.reject({msgDetail: 'unstable network'})
        } else {
            return Promise.reject({msgDetail: '网络连接异常'})
        }
    });
}

export const authenticationInterceptor = (onAuthenticationFailure: Function) => {
    axios.interceptors.response.use((response) => {
        return response;
    }, function (reason) {
        if (reason.response.status === 401) {
            onAuthenticationFailure();
        }
        return Promise.reject(reason);
    });

}