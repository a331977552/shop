import {get, httpDelete} from "../HttpClient";
import {ProductSpecModel} from "../../model";
import {PageModel, PageQueryModel, ProductSpecQueryModel} from "../../model";


export function getProductSpecListAPI(pageQueryModel: PageQueryModel<ProductSpecQueryModel>) {
    return get<PageModel<ProductSpecModel>>
    ("/api-gateway/product-service/api/product/spec",
        pageQueryModel.example);
}

export function deleteProductSpecAPI(id:number) {
    return httpDelete<string>("/api-gateway/product-service/api/product/spec"+ id);
}
