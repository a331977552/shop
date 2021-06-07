// import {post, get} from "../HttpClient";
import {ResultModel,ProductListModel} from "../../model";


export function getProductListAPI() {

    return new Promise<ResultModel<ProductListModel>>((resolve, reject) => {
        setTimeout(() => {
            resolve({
                timestamp: '2020-12-12',
                code: 200,
                msg: 'success',
                result:{products:[{
                    id:'112321134123',
                        name:'microphone 2'
                    },
                        {
                            id:'2321321',
                            name:'microphone 4'
                        },
                        {
                            id:'123123',
                            name:'microphone 3'
                        },{
                            id:'5454',
                            name:'microphone 5'
                        },
                        {
                            id:'5454',
                            name:'microphone 5'
                        },
                        {
                            id:'5454',
                            name:'microphone 5'
                        },{
                            id:'5454',
                            name:'microphone 5'
                        },{
                            id:'5454',
                            name:'microphone 5'
                        },
                        {
                            id:'5454',
                            name:'microphone 5'
                        },{
                            id:'5454',
                            name:'microphone 5'
                        },
                    ]}})
        }, 300);
    });

    // return  get<HomeModel>("/api-gateway/home-service");
}
