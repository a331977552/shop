import {get, httpDelete, post, put} from "../store/HttpClient";
import {BrandModel} from "../model";
import {PageModel, BrandQueryModel} from "../model";


export function getBrandListAPI(queryVO:BrandQueryModel) {
    return get<PageModel<BrandModel>>
    ("/api-gateway/product-service/api/brand/all",
        queryVO);
}

export function deleteBrandAPI(id:number) {
    return httpDelete<string>("/api-gateway/product-service/api/brand/" + id);
}

export function addBrandAPI(record:BrandModel) {
    return post<BrandModel>("/api-gateway/product-service/api/brand/",record);
}
export function updateBrandAPI(record:BrandModel) {
    return put<string>("/api-gateway/product-service/api/brand/",record);
}

export function getBrandAPI(id:number|string) {
    return get<BrandModel>("/api-gateway/product-service/api/brand/"+id);
}
