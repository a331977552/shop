import React, {useEffect, useState} from 'react';
import {Tag, Space, Button, Table} from "antd";
import {CategoryModel, ProductSpecModel} from "../../model";
import {useAppDispatch, useAppSelector} from "../../store/hooks";
import {getProductSpecList, selectProductSpecReducer} from "../../store/slices/productSpecSlice";
import { useHistory} from "react-router-dom";
import ProductSpecOperation from "./ProductSpecOperation";
import {selectCategoryDataReducer} from "../../store/slices/cateogrySlice";
import {findCategoryByID} from "../category/CategoryConvertor";
import StatusView from "../../components/StatusView";
import {paramParser} from "../../services";

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
        render: (text: string, record: ProductSpecModel) => {
            return text?.split("\n").map(value => <Tag>{value}</Tag>)
        }
    },
    {
        title: '是否能被检索',
        dataIndex: 'searchable',
        key: 'searchable',
        render: (text: boolean, record: ProductSpecModel) => {
            return text ? '可以' : "否"
        }
    },
    {
        title: '操作',
        dataIndex: '',
        key: 'x',
        render: (text: string, record: ProductSpecModel) => {
            return <ProductSpecOperation record={record}/>
        }
    },
];


function ProductSpecPage() {
    let [category, setCategory] = useState<CategoryModel>();
    let appDispatch = useAppDispatch();
    let history = useHistory();
    let spec = useAppSelector(selectProductSpecReducer);
    let cate = useAppSelector(selectCategoryDataReducer)?.items as CategoryModel[];
    let parsedQs = paramParser(history.location.search);
    const cateID = parsedQs['cid'] as string;
    useEffect(() => {
        setCategory(findCategoryByID(cate, +cateID));
        appDispatch(getProductSpecList({example: {categoryId: +cateID}}))
    }, [appDispatch,cateID,setCategory,cate]);
    const {data, errorMsg, status} = spec;

    function onAddClick() {
        history.push("/product/spec/add")
    }

    function onRetry() {
        appDispatch(getProductSpecList({example: {categoryId: +cateID}}))
    }

    return (
        <StatusView retry={onRetry} status={status} errorMsg={errorMsg}>
            <div style={{display: 'flex', height: '100%', flexDirection: 'column'}}>
                <h2 style={{textAlign:'center'}}>商品规格</h2>
                <div><Button style={{float: 'right'}} onClick={onAddClick}>添加商品规格</Button>
                    所在分类:<b>{category?.name}</b>
                </div>
                <div style={{width: '100%', flex: '1 0 0px', overflow: 'auto', marginTop: '10px'}}>
                    <Table childrenColumnName={"null"} loading={status === 'loading'} rowKey={"id"}
                           dataSource={data?.items}
                           columns={columns}
                           pagination={{defaultPageSize: 20, total: data?.totalElements}}
                    />
                </div>
            </div>
        </StatusView>

    );
}

export default ProductSpecPage;