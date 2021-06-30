import React from 'react';
import {OrderModel, RouterState} from "../../../model";
import {Button, Popconfirm, Space} from "antd";
import {useHistory} from "react-router-dom";


function OrderOperation(order: OrderModel) {
    let history = useHistory<RouterState>();
    function onOrderDetailClick() {
        history.push({pathname:"/order/detail",search:'oid='+order.id,state:{updateMenu:false}})
    }

    const orderDelete = <Popconfirm title={"确认要删除订单吗"}>  <Button type={'link'} danger size={"small"}>删除订单</Button></Popconfirm>;
    const orderTrack = <Button type={'link'} size={"small"}>物流追踪</Button>;

    const statusViewMap:{[key:string]:JSX.Element} = {
        'UNPAID':orderDelete,
        'PAID':<Button size={"small"}>发货</Button>,
        'SHIPPING':orderTrack,
        'SHIPPED':orderTrack,
        'CLOSED':orderDelete,
        'FINISHED':orderTrack,
        'REFUND':<Button>退货中</Button>,//todo
    }

    return (
        <Space size={"small"}>
            <Button size={"small"}
                    type={'link'}
                    onClick={onOrderDetailClick}
                    style={{padding: '0px'}}>详情</Button>
            {statusViewMap[order.status]}
        </Space>
    );
}

export default OrderOperation;