import {get, httpDelete, post, put} from "../HttpClient";
import {ProductSpecModel} from "../../model";
import {PageModel, PageQueryModel, ProductSpecQueryModel} from "../../model";


export function getProductSpecListAPI(pageQueryModel: PageQueryModel<ProductSpecQueryModel>) {
    return get<PageModel<ProductSpecModel>>
    ("/api-gateway/product-service/api/product/spec",
        pageQueryModel.example);
}

export function deleteProductSpecAPI(id:number) {
    return httpDelete<string>("/api-gateway/product-service/api/product/spec/"+ id);
}


export function getProductSpecAPI(id:number) {
    return get<ProductSpecModel>("/api-gateway/product-service/api/product/spec/"+ id);
}

export function AddSpecAPI(value:ProductSpecModel) {
    return post<ProductSpecModel>("/api-gateway/product-service/api/product/spec/",value);
}
export function updateSpecAPI(value:ProductSpecModel) {
    return put<ProductSpecModel>("/api-gateway/product-service/api/product/spec/",value);
}