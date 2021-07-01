import React from 'react';
import {KeyStr, ProductModel} from "../../../model";
import {Table} from "antd";
import ProductImage from "../ProductImage";
import { TableItem} from "../ProductCommon";

const keyStrMap:KeyStr = {
    "sales":"销量",
    "stock":'库存',
    "price":'价格',
    'img':'图片'
}

export const columnsPart2 =
    [
        {
            title: '库存',
            dataIndex: 'stock',
        },
        {
            title: '价格',
            dataIndex: 'price',
            render:(text:string,record:KeyStr,index:number)=> "¥ "+text
        },
        {
            title: '库存',
            dataIndex: 'stock',
        },
        {
            title: '图片',
            dataIndex: 'img',
            render:(text:string,record:KeyStr,index:number)=>
                     (text?<ProductImage url={text}/>:text)
        }
    ]
function AttrContent(record: ProductModel) {
    if (!record.skuList ||!record.skuList.length)
        return null;
    const skuList = (record.skuList || []).map(sku => {
        const attribute = JSON.parse(sku.attribute) as KeyStr;
        const copiedSku = {...sku,...attribute} as any;
        delete copiedSku.attribute;
        return copiedSku;
    })
    if (!skuList || skuList.length == 0)
        return null;


    const columnPart1 = Object.keys(JSON.parse(record.skuList[0].attribute)).map(key => ({
        title: key,
        dataIndex: key
    })) as TableItem[];
    console.log(skuList);

    return (
        // scroll={{ y: 240 }}  fixe header on top
        <Table scroll={{ y: 240 }} style={{width:'100%', maxHeight:'400px', overflow:'auto'}} columns={[...columnPart1,...columnsPart2]} dataSource={skuList} rowKey={"id"}
               pagination={false}
        />
    );
}

export default AttrContent;