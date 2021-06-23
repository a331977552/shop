import {PageModel, PageQueryModel, ProductModel, ProductQueryModel} from "../model";
import {get, httpDelete, post} from "../store/HttpClient";


export function getProductListAPI(pageQueryModel:PageQueryModel<ProductQueryModel>) {

    return get<PageModel<ProductModel>>("/api-gateway/product-service/api/product/"+pageQueryModel.currentPage+"/"+pageQueryModel.pageSize,pageQueryModel.example)

}
export function deleteProductAPI(id:string) {

    return httpDelete<string>("/api-gateway/product-service/api/product/"+id)

}

export function ProductAddAPI(product:ProductModel) {

    return post<ProductModel>("/api-gateway/product-service/api/product/",product)

}
