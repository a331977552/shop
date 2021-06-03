// Add a request interceptor
import axios from "axios";
import {getToken} from "./TokenConfig";


export function setTokenToCommonHeader() {
    let token = getToken();
    if (token) {
        axios.defaults.headers.common['Authorization'] = 'Bearer ' + token;
    } else {
        console.error("addTokenToCommonHeader: token doesn't exist");
    }
}

export function removeTokenFromCommonHeader() {
    delete axios.defaults.headers.common['Authorization'];
}


function addRequestInterceptor() {
    axios.interceptors.request.use(function (config) {
        // Do something before request is sent
        return config;
    }, function (error) {
        // Do something with request error
        return Promise.reject(error);
    });
}

export function addResponseTransformInterceptor() {
// Add a response interceptor
    axios.interceptors.response.use((response) => {
        // Any status code that lie within the range of 2xx cause this function to trigger
        // Do something with response data
        console.log("http success:", response,response.data);
        return response.data;
    }, function (reason) {
        // Any status codes that falls outside the range of 2xx cause this function to trigger
        // Do something with response error
        console.log("http error, reason:",reason.toJSON());
        if (reason.response)
            return Promise.reject(reason.response.data);
        else if (reason.request) {
            console.warn(reason.toJSON())
            return Promise.reject({msgDetail: 'unstable network'})
        } else {
            console.warn(reason.toJSON())
            return Promise.reject({msgDetail: 'network error'})
        }
    });
}