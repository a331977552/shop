import React, {useEffect, useState} from 'react';
import {Button, Col, Form, Row, Checkbox, message, Table} from "antd";
import TextArea from "antd/es/input/TextArea";
import {ProductAttrModel, ProductModel} from "../../model";
import {getProductAttrListAPI} from "../../api/ProductAttrAPI";

const CheckboxGroup = Checkbox.Group;
const columnsPart2 =
    [
        {
            title: '价格',
            dataIndex: 'price',
            key: 'price',
        },
        {
            title: '库存',
            dataIndex: 'stock',
            key: 'stock',
        },
        {
            title: '图片',
            dataIndex: 'img',
            key: 'img'
        }
    ]
function AttrAddForm(props: {
    setProductModel: (productModel: ProductModel | undefined) => void,
    productModel: ProductModel
}) {
    const {setProductModel, productModel} = props;
    const [productAttrs, setProductAttrs] = useState<Array<ProductAttrModel>>();
    const [columns,setColumns] = useState<Array<any>>();
    const [dataSource,setDataSource] = useState<Array<any>>();
    const [form] = Form.useForm();

    useEffect(() => {
        setColumns(undefined);
        getProductAttrListAPI({example: {categoryId: productModel?.category}}).then((result) => {
            const items = result.result?.items;
            setProductAttrs(items);

        }).catch((error) => {
            message.error(error.msgDetail, 3);
        })
    }, [setProductAttrs, productModel?.category]);



    function onAttrValueChange(changedFields: any, allFields: { [key: string]: string[] }) {
        setProductModel({...productModel, attrs: allFields})
    }

    function onRefreshClick() {
        form.validateFields().then((fieldsValue:Array<{[key:string]:string[]|string}>)=>{
            const columnsPart1 = Object.keys(fieldsValue).map(key=>({title:key, dataIndex:key,key:key}))
            setColumns(columnsPart1.concat(columnsPart2));
            Object.keys(fieldsValue).map(key=>{

            });
            const localeValues= Object.values(fieldsValue);

            console.log(fieldsValue,localeValues)
        })
    }

    return (((productAttrs?.length || 0) === 0) ? null :
            <Row justify={'center'}
                 style={{width: "100%", marginBottom: '20px'}}
            >
                <Col
                    style={{backgroundColor: '#eeeeee', padding: "10px 20px", border: '1'}}
                    xs={{span: 24}} sm={{span: 14}}
                >
                    <h3>产品属性</h3>
                    <Form
                        form={form}
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
                        name={"spec"}
                        onValuesChange={onAttrValueChange}
                    >
                        {
                            productAttrs?.map(attr =>
                                <Form.Item key={attr.id} name={attr.name} label={attr.name}
                                           rules={[{required:true,message:attr.name+" 不能为空"}]}
                                           initialValue={(productModel?.attrs || {})[attr.name]}
                                >
                                    {attr.entryMethod === 'custom' ? <TextArea  rows={4}/> :
                                        <CheckboxGroup
                                            options={attr.values?.map(val =>
                                                ({label: val.value, value: String(val.id)}))}/>
                                    }
                                </Form.Item>
                            )
                        }
                        {
                            columns && <Table style={{width:'100%'}}  size="middle" rowKey={'id'} columns={columns}
                                              // dataSource={attrDataSource}
                            />
                        }
                        <Form.Item wrapperCol={
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
                            <Button onClick={onRefreshClick}>重绘列表
                            </Button>
                        </Form.Item>
                    </Form>
                </Col>
            </Row>
    );
}

export default AttrAddForm;