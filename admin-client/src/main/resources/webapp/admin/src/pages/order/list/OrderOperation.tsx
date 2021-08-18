import React, {useState} from 'react';
import {OrderModel, RouterState} from "../../../model";
import {Button, message, Modal, Popconfirm, Space} from "antd";
import {useHistory} from "react-router-dom";
import {deleteOrderAPI} from "../../../api/OrderAPI";
import {useDispatch} from "react-redux";
import {deleteOrderLocally} from '../../../store/slices/orderSlice';
import OrderShippingPage from "../shipping/OrderShippingPage";


function OrderOperation(order: OrderModel) {
    let history = useHistory<RouterState>();
    const [modelAttrVisible, setModelAttrVisible] = useState<boolean>(false);

    let dispatch = useDispatch();

    function onOrderDetailClick() {
        history.push({pathname: "/order/detail", search: 'oid=' + order.id, state: {updateMenu: false}})
    }

    const orderDelete = <Popconfirm title={"确认要删除订单吗"} onConfirm={onDeleteClick}>
        <Button type={'link'} danger size={"small"}>删除订单</Button></Popconfirm>;
    const orderTrack = <Button type={'link'} size={"small"}>物流追踪</Button>;

    function onDeleteClick() {
        const key = 'delete_order';
        message.loading({content: '删除中', key: key});
        deleteOrderAPI(order.id as string).then(() => {
            message.success({content: "删除成功!", key: key, duration: 1})
            dispatch(deleteOrderLocally(order.id as string));
        }).catch((error) => {
            console.log(error)
            message.error({content: "删除失败,请检查网络", key: key, duration: 3})
        })
    }


    function onAttrCancel() {
        setModelAttrVisible(false);
    }

    function OnDeliveryClick() {
        setModelAttrVisible(true)
        // history.push({pathname:"/order/shipping",search:'oid='+order.id,state:{updateMenu:false}})
    }

    const statusViewMap: { [key: string]: JSX.Element } = {
        'UNPAID': orderDelete,
        'PAID': <Button type={'link'} onClick={OnDeliveryClick} size={"small"}>发货</Button>,
        'SHIPPING': orderTrack,
        'SHIPPED': orderTrack,
        'CLOSED': orderDelete,
        'FINISHED': orderTrack,
        'REFUND': <Button type={'link'}>退货中</Button>,//todo
    }

    return (
        <Space size={"small"}>
            <Button size={"small"}
                    type={'link'}
                    onClick={onOrderDetailClick}
                    style={{padding: '0px'}}>详情</Button>
            {statusViewMap[order.status]}
            <Modal destroyOnClose={true}
                   title={<span>订单<span style={{fontWeight: 'bold'}}>  {order.orderNum} </span>发货地址</span>}
                   centered={true} onCancel={onAttrCancel} footer={null} closable={true} visible={modelAttrVisible}>
                <OrderShippingPage oid={order.id as string}/>
            </Modal>
        </Space>
    );
}

export default OrderOperation;