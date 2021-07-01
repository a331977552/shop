import {OrderModel, OrderQueryModel, PageModel, PageQueryModel} from "../model";
import {get, httpDelete, put} from "../store/HttpClient";

export function getOrderListAPI(pageQueryModel:PageQueryModel<OrderQueryModel>) {

    return get<PageModel<OrderModel>>("/api-gateway/order-service/api/order/"+pageQueryModel.currentPage+"/"+pageQueryModel.pageSize,pageQueryModel.example)

}
export function deleteOrderAPI(orderId:string) {

    return httpDelete<string>("/api-gateway/order-service/api/order/"+orderId)

}