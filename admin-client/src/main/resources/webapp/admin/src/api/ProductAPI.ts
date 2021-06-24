import {PageModel, PageQueryModel, ProductModel, ProductQueryModel} from "../model";
import {get, httpDelete, post, put} from "../store/HttpClient";


export function getProductListAPI(pageQueryModel:PageQueryModel<ProductQueryModel>) {

    return get<PageModel<ProductModel>>("/api-gateway/product-service/api/product/"+pageQueryModel.currentPage+"/"+pageQueryModel.pageSize,pageQueryModel.example)

}
export function deleteProductAPI(id:string) {

    return httpDelete<string>("/api-gateway/product-service/api/product/"+id)

}

export function productAddAPI(product:ProductModel) {

    return post<ProductModel>("/api-gateway/product-service/api/product/",product)

}

export function productUpdateAPI(product:ProductModel) {

    return put<ProductModel>("/api-gateway/product-service/api/product/",product)

}
export function getProductAPI(pid:string) {

    return get<ProductModel>("/api-gateway/product-service/api/product/"+pid)

}
