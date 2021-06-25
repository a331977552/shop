import React, {useEffect, useState} from 'react';
import {Tag, Space, Button, Table} from "antd";
import {CategoryModel, ProductAttrModel} from "../../model";
import {useAppDispatch, useAppSelector} from "../../store/hooks";
import { useHistory} from "react-router-dom";
import ProductSpecOperation from "./ProductAttrOperation";
import {selectCategoryDataReducer} from "../../store/slices/cateogrySlice";
import {findCategoryByID} from "../category/CategoryConvertor";
import StatusView from "../../components/StatusView";
import {getProductAttrList, selectProductAttrReducer} from "../../store/slices/productAttrSlice";
import {paramParser} from "../../services";

const columns = [
    {
        title: '序号(编号)',
        dataIndex: 'id',
        key: 'id',
        render: (text: string, record: ProductAttrModel, index: number) => {
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
        render: (text: string, record: ProductAttrModel) => {
            return text === 'single' ? '单选' : text === 'multiple' ? '多选' : '未知';
        }
    },
    {
        title: '录入方式',
        dataIndex: 'entryMethod',
        key: 'entryMethod',
        render: (text: string, record: ProductAttrModel) => {
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
        render: (text: string, record: ProductAttrModel,index:number) => {
            return  record.values.map(value => <Tag key={value.value}>{value.value}</Tag>)

        }
    },
    {
        title: '是否能被检索',
        dataIndex: 'searchable',
        key: 'searchable',
        render: (text: boolean, record: ProductAttrModel) => {
            return text ? '可以' : "否"
        }
    },
    {
        title: '操作',
        dataIndex: 'x',
        key: 'x',
        render: (text: string, record: ProductAttrModel) => {
            return <ProductSpecOperation record={record}/>
        }
    },
];


function ProductAttrPage() {
    let [category, setCategory] = useState<CategoryModel>();
    let appDispatch = useAppDispatch();
    let history = useHistory();
    let spec = useAppSelector(selectProductAttrReducer);
    let cate = useAppSelector(selectCategoryDataReducer)?.items as CategoryModel[];
    let parsedQs = paramParser(history.location.search);
    const cateID = parsedQs['cid'] as string;

    useEffect(() => {
        if (cateID) {
            setCategory(findCategoryByID(cate, +cateID));
            appDispatch(getProductAttrList({example: {categoryId: +cateID}}))
        } else {
            history.push("/")
        }
    }, [appDispatch, cateID,history, cate]);

    const {data, errorMsg, status} = spec;

    function onAddClick() {
        history.push("/attr/add")
    }

    function onRetry() {
        appDispatch(getProductAttrList({example: {categoryId: +cateID}}))
    }

    return (
        <StatusView retry={onRetry} status={status} errorMsg={errorMsg}>
            <div style={{display: 'flex', height: '100%', flexDirection: 'column'}}>
                <h2 style={{textAlign: 'center'}}>商品属性</h2>
                <div><Button style={{float: 'right'}} onClick={onAddClick}>添加商品属性</Button>
                    所在分类:<b>{category?.name}</b>
                </div>
                <div style={{width: '100%', flex: '1 0 0px', overflow: 'auto', marginTop: '10px'}}>
                    <Table  loading={status === 'loading'} rowKey={"id"}
                           dataSource={data?.items}
                           columns={columns}
                           pagination={{defaultPageSize: 20, total: data?.totalElements}}
                    />
                </div>
            </div>
        </StatusView>

    );
}

export default ProductAttrPage;
