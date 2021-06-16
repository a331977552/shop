import {get, httpDelete, post, put} from "../store/HttpClient";
import {ProductAttrModel, ProductAttrQueryModel,PageModel, PageQueryModel} from "../model";


export function getProductAttrListAPI(pageQueryModel: PageQueryModel<ProductAttrQueryModel>) {
    return get<PageModel<ProductAttrModel>>
    ("/api-gateway/product-service/api/category/attr",
        pageQueryModel.example);
}

export function deleteProductAttrAPI(id:number) {
    return httpDelete<string>("/api-gateway/product-service/api/category/attr/"+ id);
}


export function getProductAttrAPI(id:number) {
    return get<ProductAttrModel>("/api-gateway/product-service/api/category/attr/"+ id);
}

export function AddAttrAPI(value:ProductAttrModel) {
    return post<ProductAttrModel>("/api-gateway/product-service/api/category/attr/",value);
}
export function updateAttrAPI(value:ProductAttrModel) {
    return put<ProductAttrModel>("/api-gateway/product-service/api/category/attr/",value);
}