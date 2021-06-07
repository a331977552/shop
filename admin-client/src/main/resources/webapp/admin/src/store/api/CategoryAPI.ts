import {post, get} from "../HttpClient";
import {ResultModel} from "../../model";
import {CategoryModel} from "../../model";
import {PageModel, PageQueryModel} from "../../model";


export function getCategoryListAPI(pageQueryModel:PageQueryModel<any>) {

    return get<PageModel<CategoryModel>>("/api-gateway/product-service/api/category/all/"+pageQueryModel.currentPage+"/"+pageQueryModel.pageSize);


    // return  get<HomeModel>("/api-gateway/home-service");
}
