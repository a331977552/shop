import axios from "axios";
import {ResultModel} from "../model";

export function post<T>(url: string, data: any): Promise<ResultModel<T>> {
    return axios.post<ResultModel<T>, ResultModel<T>>(url, data).then
    (responseResult => {
        return Promise.resolve(responseResult);
    }).catch(reasonResult => {
        return Promise.reject(reasonResult)
    });
}



export function get<T>(url: string): Promise<ResultModel<T>> {
    return axios.get<ResultModel<T>, ResultModel<T>>(url).then
    (responseResult => {
        return Promise.resolve(responseResult);
    }).catch(reasonResult => {
        return Promise.reject(reasonResult)
    });
}


export function put<T>(url: string, data: any): Promise<ResultModel<T>> {
    return axios.put<ResultModel<T>, ResultModel<T>>(url, data).then
    (responseResult => {
        return Promise.resolve(responseResult);
    }).catch(reasonResult => {
        return Promise.reject(reasonResult)
    });
}


