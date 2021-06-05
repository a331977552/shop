import {post, get} from "../HttpClient";
import HomeModel from "../../model/HomeModel";
import ResultModel from "../../model/ResultModel";


export function getHomeInfoAPI() {

    return new Promise<HomeModel>((resolve, reject) => {
        setTimeout(() => {
            resolve({test: 'HAHAH12663'});

        }, 2000);
    });

    // return  get<HomeModel>("/api-gateway/home-service");
}
