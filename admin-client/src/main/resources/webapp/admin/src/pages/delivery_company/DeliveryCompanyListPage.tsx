import React, {useEffect} from 'react';
import {Button, Table, Tag, Space} from "antd";
import {DeliveryCompanyModel} from "../../model";
import DeliveryOperation from "./DeliveryOperation";
import StatusView from "../../components/StatusView";
import {useAppDispatch, useAppSelector} from "../../store/hooks";
import {getDeliveryCompanyList, selectDeliveryCompanyReducer} from "../../store/slices/deliveryCompanySlice";


const columns = [
    {
        title: '序号(编号)',
        dataIndex: 'id',
        key: 'id',
        render: (text: string, record: DeliveryCompanyModel, index: number) => {
            return <Space><span>{index}</span> <Tag color={"lime"}>{record.id}</Tag></Space>
        }
    },
    {
        title: '名称',
        dataIndex: 'name',
        key: 'name',
    },

    {
        title: '相关信息',
        dataIndex: 'info',
        key: 'info',
    },
    {
        fixed: false,
        title: '操作',
        dataIndex: '',
        key: 'x',
        render: (tex: string, record: DeliveryCompanyModel) => <DeliveryOperation />
    ,
},
];


function DeliveryCompanyListPage() {
    let dispatch = useAppDispatch();
    let deliveryCompanyReducer = useAppSelector(selectDeliveryCompanyReducer)
    const {status, errorMsg, data} = deliveryCompanyReducer;
    useEffect(() => {
        dispatch(getDeliveryCompanyList());
    }, [dispatch]);

    function onRetry() {
        dispatch(getDeliveryCompanyList());
    }

    function onAddClick() {
        // history.push({pathname:"/brand/add",state:{updateMenu:false}})
    }


    return (
        <div style={{display: 'flex', height: '100%', flexDirection: 'column'}}>
    <h2 style={{textAlign: 'center'}}>商品品牌</h2>
    <div style={{display: 'flex', justifyContent: 'space-between'}}>
    {/*<Search placeholder="品牌名称" onSearch={onSearch} loading={status === 'loading'} style={{width: 200}}/>*/}
    <Button style={{float: 'right'}} onClick={onAddClick}>添加品牌</Button>
        </div>
        <div style={{width: '100%', flex: '1 0 0px', overflow: 'auto', marginTop: '10px'}}>
    <StatusView retry={onRetry} status={status} loadOnce={true}  errorMsg={errorMsg}>
    <Table childrenColumnName={"null"} rowKey={"id"}
    dataSource={data}
    columns={columns}
    pagination={{defaultPageSize: 20, total: data?.length}}
    />
    </StatusView>
    </div>
    </div>
);
}

export default DeliveryCompanyListPage;


