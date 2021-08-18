

import {get, httpDelete, post, put} from "../store/HttpClient";
import {DeliveryCompanyModel,DeliveryCompanyQueryModel} from "../model";
import {PageModel} from "../model";


export function getDeliveryCompanyListAPI(queryVO:DeliveryCompanyQueryModel|undefined) {
    return get<PageModel<DeliveryCompanyModel>>
    ("/api-gateway/order-service/api/delivery_company/all",
        queryVO);
}
