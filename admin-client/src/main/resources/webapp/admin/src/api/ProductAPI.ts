import {PageModel, PageQueryModel, ProductModel, ProductQueryModel} from "../model";
import {get} from "../store/HttpClient";


export function getProductListAPI(pageQueryModel:PageQueryModel<ProductQueryModel>) {

    return get<PageModel<ProductModel>>("/api-gateway/product-service/api/product/"+pageQueryModel.currentPage+"/"+pageQueryModel.pageSize,pageQueryModel.example)

}
