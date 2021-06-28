import {OrderModel, OrderQueryModel, PageModel, PageQueryModel} from "../model";
import {get} from "../store/HttpClient";

export function getOrderListAPI(pageQueryModel:PageQueryModel<OrderQueryModel>) {

    return get<PageModel<OrderModel>>("/api-gateway/order-service/api/order/"+pageQueryModel.currentPage+"/"+pageQueryModel.pageSize,pageQueryModel.example)

}