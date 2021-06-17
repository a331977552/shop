import React, {useEffect} from 'react';
import { Button, Table, Space} from "antd";
import {getProductList, selectProductReducer} from "../../store/slices/productSlice";
import {useHistory} from "react-router-dom";
import {useAppDispatch, useAppSelector} from "../../store/hooks";
import StatusView from "../../components/StatusView";
import { ProductModel} from "../../model";
import ProductSearch from "./ProductSearch";
import ProductOperation from "./ProductOperation";

const statusMap:{[key:string]:string} = {
    "ON_SALE":"上架中",
    "OUT_OF_ORDER":"已下架",
}

const columns = [
    {
        title: '序号',
        key: 'id',
        render(text:string,record:ProductModel,index:number){
            return index;
        }
    },
    {
        title: '商品名',
        dataIndex: 'name',
        key: 'name',
    },

    {
        title: '状态',
        dataIndex: 'status',
        key: 'status',
        render(text:string,record:ProductModel,index:number){
            return statusMap[text];
        }
    },
    {
        title: '图片',
        dataIndex: 'thumbnailImg',
        key: 'thumbnailImg',
    },
    {
        title: '销量',
        dataIndex: 'sales',
        key: 'sales',
    },
    {
        title: '价格',
        dataIndex: 'price',
        key: 'price',
    },
    {
        title: '排序',
        dataIndex: 'priority',
        key: 'priority',
    },
    {
        title: 'sku',
        dataIndex: 'sku',
        key: 'sku',
    },
    {
        title: '操作',
        dataIndex: '',
        key: 'x',
        render: (text:any, record:ProductModel,index:number) =>
            <ProductOperation {...record}/>
    },
];


export default function ProductListPage() {
    let dispatch = useAppDispatch();
    let productReducer = useAppSelector(selectProductReducer);
    const { status, errorMsg,data} = productReducer;
    useEffect(() => {
        dispatch(getProductList({currentPage:0,pageSize:20}));
    }, [])

    function onRetry() {
        dispatch(getProductList({currentPage:data?data.currentPage:0,pageSize:data?data.pageSize:20}));
    }

    function onPageChange(page:number, pageSize?:number) {
        dispatch(getProductList({currentPage:Math.max(0,--page),pageSize:pageSize}));
    }
    return (
        <div style={{display: 'flex', height: '100%', flexDirection: 'column'}}>
            <ProductSearch/>
            <StatusView status={status} retry={onRetry} loadOnce={true} errorMsg={errorMsg}>
                <div style={{width: '100%', flex: '1 0 0px', overflow: 'auto', marginTop: '10px'}}>
                    <Table loading={status === 'loading'} rowKey={"id"} dataSource={data?.items} columns={columns}
                    pagination={data&&{total:data.totalElements,
                        current:(data.currentPage+1),pageSize:data.pageSize,onChange:onPageChange}}
                    />
                </div>
            </StatusView>
        </div>
    );
}







