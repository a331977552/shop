import React, {useEffect} from 'react';
import { Table} from "antd";
import {useAppDispatch, useAppSelector} from "../../store/hooks";
import StatusView from "../../components/StatusView";
import { OrderModel} from "../../model";
import OrderSearch from "./OrderSearch";
import OrderOperation from "./OrderOperation";
import {getOrderList, selectOrderReducer} from "../../store/slices/orderSlice";
import dayjs from "dayjs";
const payMap:{[key:string]:string} = {
    "wechat":"微信",
    "alipay":"支付宝",
}
const sourceMap:{[key:string]:string} = {
    "web":"网页",
    "android_app":"安卓",
    "ios_app":"苹果",
    "mini_program":'小程序'
}


const statusMap:{[key:string]:string} = {
    'UNPAID':'未支付',
    'PAID':'已支付',
    'SHIPPING':'快递中',
    'SHIPPED':'已投递',
    'FINISHED':'已完成',
    'REFUND':'已退款',
    'CLOSED':'已关闭'
}



const columns = [
    {
        title: '序号',
        key: 'id',
        render(text:string,record:OrderModel,index:number){
            return index;
        }
    },
    {
        title: '订单号',
        dataIndex: 'orderNum',
        key: 'orderNum',
    },
    {
        title: '用户账号',
        dataIndex: 'username',
        key: 'username'
    },
    {
        title: '订单来源',
        dataIndex: 'orderSource',
        key: 'orderSource',
        render(text:string,record:OrderModel,index:number){
            return sourceMap[text];
        }

    },
    {
        title: '支付方式',
        dataIndex: 'payMethod',
        key: 'payMethod',
        render(text:string,record:OrderModel,index:number){
            return payMap[text];
        }
    },
    {
        title: '总价',
        dataIndex: 'totalPrice',
        key: 'totalPrice',
        render(text:string,record:OrderModel,index:number){
            return "￥"+text
        }
    },
    {
        title: '状态',
        dataIndex: 'status',
        key: 'status',
        render(text:string,record:OrderModel,index:number){
            return statusMap[text];
        }
    },

    {
        title: '创建时间',
        dataIndex: 'createdTime',
        key: 'createdTime',
        render(text:string,record:OrderModel,index:number){
            return dayjs(text).format("YYYY-MM-DD HH:mm:ss");
        }
    },

    {
        title: '操作',
        dataIndex: '',
        key: 'x',
        render: (text:any, record:OrderModel,index:number) =>
            <OrderOperation {...record}/>
    },
];

function OrderListPage() {
    let dispatch = useAppDispatch();
    let orderReducer = useAppSelector(selectOrderReducer);
    const { status, errorMsg,data} = orderReducer;
    useEffect(() => {
        dispatch(getOrderList({currentPage:0,pageSize:10}));
    }, [dispatch])

    function onRetry() {
        dispatch(getOrderList({currentPage:data?data.currentPage:0,pageSize:data?data.pageSize:10}));
    }

    function onPageChange(page:number, pageSize?:number) {
        dispatch(getOrderList({currentPage:Math.max(0,--page),pageSize:pageSize}));
    }
    return (
        <div style={{display: 'flex', overflow: 'auto',height: '100%', flexDirection: 'column'}}>
            <OrderSearch/>
            <StatusView status={status} retry={onRetry} loadOnce={true} errorMsg={errorMsg}>
                <div style={{width: '100%', flex: '1 0 0px',  marginTop: '10px'}}>
                    <Table loading={status === 'loading'} rowKey={"id"} dataSource={data?.items} columns={columns}
                           pagination={data&&{total:data.totalElements,
                               current:(data.currentPage+1),pageSize:data.pageSize,onChange:onPageChange}}
                    />
                </div>
            </StatusView>
        </div>
    );
}

export default OrderListPage;




