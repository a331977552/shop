// import {post, get} from "../HttpClient";
import HomeModel from "../../model/HomeModel";
import ResultModel from "../../model/ResultModel";


export function getHomeInfoAPI() {

    return new Promise<ResultModel<HomeModel>>((resolve, reject) => {
        setTimeout(() => {
            resolve({
                timestamp: '2020-12-12',
                code: 200,
                msg: 'success',
                result:{test: 'loading success from HomeAPI'}});

        }, 2000);
    });

    // return  get<HomeModel>("/api-gateway/home-service");
}
