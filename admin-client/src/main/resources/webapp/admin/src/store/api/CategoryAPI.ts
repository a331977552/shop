import {get} from "../HttpClient";
import {CategoryQueryVO} from "../../model";
import {CategoryModel} from "../../model";
import {PageModel, PageQueryModel} from "../../model";


export function getCategoryListAPI(pageQueryModel:PageQueryModel<CategoryQueryVO>) {
    return get<PageModel<CategoryModel>>
    ("/api-gateway/product-service/api/category/all/"
        +pageQueryModel.currentPage+"/"+pageQueryModel.pageSize,
        {parent:pageQueryModel.example?.parent});
}
