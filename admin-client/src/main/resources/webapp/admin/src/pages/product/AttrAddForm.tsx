import React from 'react';
import {Button, Col, Form, Row, Checkbox} from "antd";
import TextArea from "antd/es/input/TextArea";
import {ProductAttrModel, ProductModel} from "../../model";
const CheckboxGroup = Checkbox.Group;


function AttrAddForm(props:{
    productAttrs:Array<ProductAttrModel>,
    setProductModel: (productModel: ProductModel | undefined) => void,
    productModel:ProductModel
}) {
    const [attrForm] = Form.useForm();
    const {productAttrs,setProductModel,productModel} = props;
    function onAttrValueChange(changedFields: any, allFields: { [key: string]: string[] }) {
        setProductModel({...productModel,attrs:allFields})
    }

    function onRefreshClick() {

    }

    return (
        <Row justify={'center'}
             style={{width: "100%", marginBottom: '20px'}}
        >
            <Col
                style={{backgroundColor: '#eeeeee', padding: "10px 20px", border: '1'}}
                xs={{span: 24}} sm={{span: 14}}
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
                    name={"spec"}
                    onValuesChange={onAttrValueChange}
                >
                    {
                        productAttrs?.map(attr =>
                            <Form.Item key={attr.id} name={attr.name} label={attr.name}
                                initialValue={(productModel?.attrs||{})[attr.name]}
                            >
                                {attr.entryMethod === 'custom' ? <TextArea rows={4}/> :
                                    <CheckboxGroup
                                        options={ attr.values?.map(val =>
                                        ({label: val.value, value: String(val.id)}))}   />
                                }
                            </Form.Item>
                        )
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