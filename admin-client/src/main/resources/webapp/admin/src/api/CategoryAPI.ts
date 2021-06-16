import {get, httpDelete, post, put} from "../store/HttpClient";
import {CategoryQueryVO} from "../model";
import {CategoryModel} from "../model";
import {PageModel, PageQueryModel} from "../model";


export function getCategoryListAPI(pageQueryModel?:PageQueryModel<CategoryQueryVO>) {
    if(!pageQueryModel){
        pageQueryModel = {currentPage:0,pageSize:100}
    }
    return get<PageModel<CategoryModel>>
    ("/api-gateway/product-service/api/category/all/"
        +pageQueryModel.currentPage+"/"+pageQueryModel.pageSize,
        {parent:pageQueryModel.example?.parent});
}
export function deleteCategoryAPI(id:number) {
    return httpDelete<string>("/api-gateway/product-service/api/category/" + id);
}

export function toggleCategoryVisibleAPI(record:CategoryModel) {
    return put<string>("/api-gateway/product-service/api/category/",record);
}
export function addCategoryAPI(record:CategoryModel) {
    return post<CategoryModel>("/api-gateway/product-service/api/category/",record);
}
export function updateCategoryAPI(record:CategoryModel) {
    return put<CategoryModel>("/api-gateway/product-service/api/category/",record);
}

export function getCategoryAPI(id:number|string) {
    return get<CategoryModel>("/api-gateway/product-service/api/category/"+id);
}
