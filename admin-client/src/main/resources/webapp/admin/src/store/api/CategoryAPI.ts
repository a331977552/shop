// import {post, get} from "../HttpClient";
import HomeModel from "../../model/HomeModel";
import ResultModel from "../../model/ResultModel";
import {CategoryModel} from "../../model/CategoryModel";


export function getCategoryListAPI() {

    return new Promise<ResultModel<Array<CategoryModel>>>((resolve, reject) => {
        setTimeout(() => {
            resolve({
                timestamp: '2020-12-12',
                code: 200,
                msg: 'success',
                result:[
                    {id:1,
                    name:'服装',
                    parent:0},
                    {id:2,
                        name:'服装',
                        parent:0},
                    {id:3,
                        name:'服装',
                        parent:0},
                    {id:4,
                        name:'服装',
                        parent:0}
                ]});

        }, 500);
    });

    // return  get<HomeModel>("/api-gateway/home-service");
}
