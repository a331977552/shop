import React, {useEffect, useState} from 'react';
import {Tag, Space, Button, Table, message} from "antd";
import {CategoryModel, ProductSpecModel} from "../../model";
import {useAppDispatch, useAppSelector} from "../../store/hooks";
import {getProductSpecList, selectProductSpecReducer} from "../../store/slices/productSpecSlice";
import {useParams, useLocation} from "react-router-dom";
import {getCategoryAPI} from "../../store/api/CategoryAPI";


const levelColor = [
    "#f50",
    "#2db7f5",
    "#87d068",
    "#108ee9",
    "gold",
    "lime",
    "green",
    "cyan",
    "blue"
]
const columns = [
    {
        title: '序号(编号)',
        dataIndex: 'id',
        key: 'id',
        render: (text: string, record: ProductSpecModel, index: number) => {
            return <Space><span>{index}</span> <Tag color={"lime"}>{record.id}</Tag></Space>
        }
    },


    {
        title: '名称',
        dataIndex: 'name',
        key: 'name',
    },
    {
        title: '选择方式',
        dataIndex: 'selectType',
        key: 'selectType',
        render: (text: string, record: ProductSpecModel) => {
            return text === 'single' ? '单选' : text === 'multiple' ? '多选' : '未知';
        }
    },
    {
        title: '录入方式',
        dataIndex: 'entryMethod',
        key: 'entryMethod',
        render: (text: string, record: ProductSpecModel) => {
            return text === 'custom' ? '手动录入' : text === 'selection' ? '从列表中选取' : '未知';
        }
    },
    {
        title: '排序',
        dataIndex: 'sort',
        key: 'sort',
    }, {
        title: '值',
        dataIndex: 'value',
        key: 'value',
    },
    {
        title: '是否能被检索',
        dataIndex: 'searchable',
        key: 'searchable',
        render: (text: string, record: ProductSpecModel) => {
            return text === 'true' ? '可以' : "否"
        }
    },
];


function ProductSpecPage() {
    let [category, setCategory] = useState<CategoryModel>();
    let appDispatch = useAppDispatch();
    let spec = useAppSelector(selectProductSpecReducer);
    const param = useParams<{ cateID: string, cateName: string }>();
    let locationStateLocation = useLocation();
    const {cateID, cateName} = param;
    console.log(param, locationStateLocation)
    useEffect(() => {
        getCategoryAPI(cateID)
            .then((result) => {
                setCategory(result.result);
            }).catch((error) => {
            message.error("加载种类错误,原因: " + error.msgDetail)
        })
        appDispatch(getProductSpecList({currentPage: 0, pageSize: 100, example: {categoryId: +cateID}}))
    }, [appDispatch, cateID, setCategory]);
    const {data, errorMsg, status} = spec;

    function onAddClick() {
    }

    return (
        status === 'error' ? <div> error: {errorMsg}</div> :
            <div style={{display: 'flex', height: '100%', flexDirection: 'column'}}>
                <div><Button style={{float: 'right'}} onClick={onAddClick}>添加商品参数</Button>
                    {category?.name}
                </div>
                <div style={{width: '100%', flex: '1 0 0px', overflow: 'auto', marginTop: '10px'}}>
                    <Table childrenColumnName={"null"} loading={status === 'loading'} rowKey={"id"}
                           dataSource={data?.items}
                           columns={columns}
                           pagination={{defaultPageSize: 20, total: data?.totalElements}}
                    />
                </div>
            </div>
    );
}

export default ProductSpecPage;