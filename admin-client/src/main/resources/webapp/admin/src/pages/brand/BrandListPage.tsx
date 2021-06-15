import React from 'react';
import {Button, Table, Tag, Space} from "antd";
import {RootState} from "../../store/store";
import {BrandModel} from "../../model";
import BrandOperation from "./BrandOperation";
import BrandSetting from "./BrandSetting";


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
    },
    {
        title: '显示',
        dataIndex: 'visible',
        key: 'visible',
    },
    {
        title: '相关信息',
        dataIndex: 'info',
        key: 'info',
    },
    {
        title: '设置',
        dataIndex: '',
        key: 'y',
        render: (text: string, record: BrandModel, index: number) => {
            return <BrandSetting {...record}/>
        }
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

    return (
        <div>123</div>
    );
}

export default BrandListPage;


