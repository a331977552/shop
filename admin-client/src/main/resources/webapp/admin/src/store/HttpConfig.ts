// Add a request interceptor
import axios from "axios";
import {getTokenFromStorage, removeTokenFromStorage} from "./TokenConfig";


export function addRequestInterceptor() {
    axios.interceptors.request.use(function (config) {
        // Do something before request is sent
        const tokenFromStorage = getTokenFromStorage();
        if (tokenFromStorage)
        {
            config.headers["Authorization"] = "Bearer "+tokenFromStorage
        }
        return config;
    }, function (error) {
        // Do something with request error
        return Promise.reject(error);
    });
}

function checkTokenValidity(reason:any) {
    if (reason.response.status === 401) {
        console.warn("token removed due to ",reason.response.status)
        removeTokenFromStorage();
    }
}
export function addResponseTransformInterceptor() {

    axios.interceptors.response.use((response) => {
        return response.data;
    }, function (reason) {
        console.log("http error:",reason.toJSON());
        if (reason.response){
            checkTokenValidity(reason);
            return Promise.reject(reason.response.data);
        }
        else if (reason.request) {
            console.warn(reason.toJSON())
            return Promise.reject({msgDetail: 'unstable network'})
        } else {
            console.warn(reason.toJSON())
            return Promise.reject({msgDetail: '网络连接异常'})
        }
    });
}