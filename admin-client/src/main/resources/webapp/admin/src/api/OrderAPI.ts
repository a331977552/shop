import {OrderModel, OrderQueryModel, PageModel, PageQueryModel, ShippingAddressModel} from "../model";
import {get, httpDelete} from "../store/HttpClient";

export function getOrderListAPI(pageQueryModel:PageQueryModel<OrderQueryModel>) {

    return get<PageModel<OrderModel>>("/api-gateway/order-service/api/order/"+pageQueryModel.currentPage+"/"+pageQueryModel.pageSize,pageQueryModel.example)

}
export function deleteOrderAPI(orderId:string) {

    return httpDelete<string>("/api-gateway/order-service/api/order/"+orderId)

}

export function getShippingAddressByOrderIdAPI(orderId:string) {

    return get<ShippingAddressModel>("/api-gateway/order-service/api/order/address?oid="+orderId)

}