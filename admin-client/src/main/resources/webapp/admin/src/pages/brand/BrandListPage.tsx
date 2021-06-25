import React, {useEffect} from 'react';
import {Button, Table, Tag, Space} from "antd";
import {BrandModel} from "../../model";
import BrandOperation from "./BrandOperation";
import BrandSetting from "./BrandSetting";
import StatusView from "../../components/StatusView";
import {useAppDispatch, useAppSelector} from "../../store/hooks";
import {getBrandList, selectBrandReducer} from "../../store/slices/brandSlice";
import {useHistory} from "react-router-dom";
import Search from "antd/es/input/Search";


const levelColor: { [key: string]: string; } = {
    "true": "#f50",
    "false": "#87d068"
}
const columns = [
    {
        title: '序号(编号)',
        dataIndex: 'id',
        key: 'id',
        render: (text: string, record: BrandModel, index: number) => {
            return <Space><span>{index}</span> <Tag color={"lime"}>{record.id}</Tag></Space>
        }
    },
    {
        title: '名称',
        dataIndex: 'name',
        key: 'name',
    },

    {
        title: '排序',
        dataIndex: 'priority',
        key: 'priority',
    },

    {
        title: '首字母',
        dataIndex: 'capitalLetter',
        key: 'capitalLetter',
    },

    {
        title: '品牌制造商',
        dataIndex: 'isManufacturer',
        key: 'isManufacturer',
        render: (text: string, record: BrandModel, index: number) => {
            return <Tag color={levelColor[String(record.isManufacturer)]}>{record.isManufacturer ? "是" : "否"}</Tag>
        }
    },
    {
        title: '显示',
        dataIndex: 'visible',
        key: 'visible',
        render: (text: string, record: BrandModel, index: number) => {
            return <BrandSetting {...record}/>
        }
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
        render: (tex: string, record: BrandModel) => <BrandOperation {...record}/>
        ,
    },
];


function BrandListPage() {
    let dispatch = useAppDispatch();
    let brandState = useAppSelector(selectBrandReducer)
    let history = useHistory();
    const {status, errorMsg, data} = brandState;
    let localName: string | undefined = undefined;
    useEffect(() => {
        dispatch(getBrandList());
    }, [dispatch]);

    function onRetry() {
        dispatch(getBrandList({name: localName}));
    }

    function onAddClick() {
        history.push("/brand/add")
    }

    function onSearch(name: string) {
        localName = name;
        dispatch(getBrandList({name}))
    }

    return (
        <div style={{display: 'flex', height: '100%', flexDirection: 'column'}}>
            <h2 style={{textAlign: 'center'}}>商品品牌</h2>
            <div style={{display: 'flex', justifyContent: 'space-between'}}>
                <Search placeholder="品牌名称" onSearch={onSearch} loading={status === 'loading'} style={{width: 200}}/>
                <Button style={{float: 'right'}} onClick={onAddClick}>添加品牌</Button>
            </div>
            <div style={{width: '100%', flex: '1 0 0px', overflow: 'auto', marginTop: '10px'}}>
                <StatusView retry={onRetry} status={status} loadOnce={true}  errorMsg={errorMsg}>
                    <Table childrenColumnName={"null"} rowKey={"id"}
                           dataSource={data?.items}
                           columns={columns}
                           pagination={{defaultPageSize: 20, total: data?.totalElements}}
                    />
                </StatusView>
            </div>
        </div>
    );
}

export default BrandListPage;


