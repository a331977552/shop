import React, {useEffect, useState} from 'react';
import {Button, Col, Form, Row, Checkbox, message, Table, InputNumber, Input, Space, Popconfirm} from "antd";
import TextArea from "antd/es/input/TextArea";
import {ProductAttrModel, ProductModel} from "../../model";
import {getProductAttrListAPI} from "../../api/ProductAttrAPI";
import {log} from "../../services";
import styled from "styled-components";

const CheckboxGroup = Checkbox.Group;
const StyledTd = styled.td`
  border: 1px solid #dddddd;
  text-align: left;
  padding: 4px;
`
const StyledTh = styled.th`
  border: 1px solid #dddddd;
  text-align: left;
  padding: 4px;
`
declare type  TableItem = {
    title: string,
    dataIndex: string
};
const columnsPart2: TableItem[] =
    [
        {
            title: '价格',
            dataIndex: 'price',
        },
        {
            title: '库存',
            dataIndex: 'stock',
        },
        {
            title: '图片',
            dataIndex: 'img',
        }
    ]

declare type  KeyVal = { [key: string]: string };
declare type  KeyVals = { [key: string]: string[] };
declare type  KeyValMix = { [key: string]: string | string[] };

function AttrAddForm(props: {
    setProductModel: (productModel: ProductModel | undefined) => void,
    productModel: ProductModel
}) {
    const {setProductModel, productModel} = props;
    const [productAttrs, setProductAttrs] = useState<Array<ProductAttrModel>>();
    const [columns, setColumns] = useState<Array<TableItem>>();
    const [dataSource, setDataSource] = useState<Array<KeyVal>>();
    const [attrForm] = Form.useForm();

    useEffect(() => {
        setColumns(undefined);
        setDataSource(undefined);
        getProductAttrListAPI({example: {categoryId: productModel?.category}}).then((result) => {
            const items = result.result?.items;
            setProductAttrs(items);

        }).catch((error) => {
            message.error(error.msgDetail, 3);
        })
    }, [setProductAttrs, productModel?.category]);


    function onAttrValueChange(changedFields: any, allFields: KeyVals) {
        console.log(allFields)
        setProductModel({...productModel, attrs: allFields})
    }

    function generateData(valueMap: KeyValMix): Array<KeyVal> {
        const data = [] as Array<{ [key: string]: string }>;
        if (Object.keys(valueMap).length === 0)
            return data;

        function _generateData(keys: string[], valueMap: KeyValMix, obj: any = {}) {
            const key = keys[0];
            let valArrayOfKey = valueMap[key];
            if (typeof valArrayOfKey === 'string') {
                valArrayOfKey = valArrayOfKey.split("\n");
            }

            valArrayOfKey.forEach(val => {
                if (keys.length === 1) {
                    data.push({...obj, [key]: val, price: '', stock: '', img: ''});
                } else {
                    _generateData(keys.slice(1), valueMap, {...obj, [key]: val})
                }
            });

        }

        _generateData(Object.keys(valueMap), valueMap, {});
        return data;
    }

    function onConfirmClick(val: any) {
    }

    function onRefreshClick() {
        attrForm.validateFields().then((fieldsValue: { [key: string]: string[] | string }) => {
            const columnsPart1 = Object.keys(fieldsValue).map(key => ({title: key, dataIndex: key}))
            setColumns(columnsPart1.concat(columnsPart2));
            setDataSource(generateData(fieldsValue));
        })
    }

    function onRecordChange(row: KeyVal, index: number, key: string, val: string) {
        const newData = [...(dataSource as Array<KeyVal>)];
        newData[index][key] = val;
        setDataSource(newData);
    }

    function onSyncAttrClick(dataIndex: string) {
        let newData = [...(dataSource as Array<KeyVal>)];
        setDataSource(newData.map(data => ({...data, [dataIndex]: newData[0][dataIndex]})));
    }


    return (((productAttrs?.length || 0) === 0) ? null :
            <Row justify={'center'}
                 style={{width: "100%", marginBottom: '20px'}}
            >
                <Col
                    style={{backgroundColor: '#eeeeee', padding: "10px 20px", border: '1'}}
                    xs={{span: 24}} sm={{span: 24}}
                >
                    <h3>产品属性</h3>
                    <Form
                        form={attrForm}
                        style={{width: '100%'}}
                        labelCol={{
                            xs: {span: 24},
                            sm: {span: 6}
                        }}
                        wrapperCol={{
                            xs: {span: 24},
                            sm: {span: 18},
                        }}
                        layout="horizontal"
                        name={"attr"}
                        onValuesChange={onAttrValueChange}
                    >
                        {
                            productAttrs?.map(attr =>
                                <Form.Item key={attr.id} name={attr.name} label={attr.name}
                                           rules={[{required: true, message: attr.name + " 不能为空"}]}
                                           initialValue={(productModel?.attrs || {})[attr.name]}
                                >
                                    {attr.entryMethod === 'custom' ? <TextArea rows={4}/> :
                                        <CheckboxGroup
                                            options={attr.values?.map(val =>
                                                ({label: val.value, value: val.value}))}/>
                                    }
                                </Form.Item>
                            )
                        }
                    </Form>
                    {columns && dataSource &&
                    <table style={{
                        borderCollapse: 'collapse',
                        width: '100%', backgroundColor: 'white'
                    }}>
                        <thead>
                        <tr>
                            {columns.map(column => <StyledTh key={column.title}>{column.title}</StyledTh>)}
                        </tr>
                        </thead>
                        <tbody>

                        {dataSource.map((row, index) => {
                            return <tr key={index}>
                                {Object.keys(row).map(key => <StyledTd
                                    key={key}>
                                    {(key === 'price' || key === 'stock') ?
                                        <Input
                                            value={row[key]}
                                            style={{width: '70px', margin: '0px', padding: '2px 0px'}}
                                            type={'number'} min={0} step={key === 'price' ? 0.01 : 1}
                                            onChange={(val) => {
                                                onRecordChange(row, index, key, val.target.value);
                                            }}
                                        /> : row[key]
                                    }
                                </StyledTd>)}
                            </tr>
                        })}
                        </tbody>
                        {(dataSource.length > 1) && <tfoot>
                        <tr>
                            {columns.map(col => (
                                <StyledTd key={col.dataIndex}>
                                    {(col.dataIndex === 'price'
                                        || col.dataIndex === 'stock') ?
                                        ((dataSource.slice(1).filter(item => (!!item[col.dataIndex])).length > 0) ?
                                                <Popconfirm title={`同步将会丢失${col.title}数据,确定吗?`}
                                                            onConfirm={() => {
                                                                onSyncAttrClick(col.dataIndex);
                                                            }}
                                                >
                                                    <Button style={{width: '70px'}}
                                                            size={"small"}>同步{col.title}</Button></Popconfirm> :
                                                <Button style={{width: '70px'}} onClick={() => {
                                                    onSyncAttrClick(col.dataIndex);
                                                }} size={"small"}>同步{col.title}</Button>
                                        ) : ""}</StyledTd>
                            ))
                            }
                        </tr>
                        </tfoot>}
                    </table>
                    }
                    <Form.Item
                        style={{marginTop: '20px'}}
                        wrapperCol={
                            {
                                xs: {
                                    span: 24,
                                    offset: 0
                                },
                                sm: {
                                    span: 20,
                                    offset: 6
                                }
                            }}
                    >
                        <Space>
                            {(dataSource?.length||0)>0?
                                <Popconfirm title={"重新生成列表,会导致当前数据丢失!"}
                                            onConfirm={onRefreshClick}
                                >
                                    <Button>重新生成列表
                                    </Button>
                                </Popconfirm>: <Button onClick={onRefreshClick}>生成列表
                                </Button>
                            }
                        </Space>
                    </Form.Item>
                </Col>
            </Row>
    );
}

export default AttrAddForm;