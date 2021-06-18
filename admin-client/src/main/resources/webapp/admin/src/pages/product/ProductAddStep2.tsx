import React, {useEffect, useState} from 'react';
import {ProductModel, ProductSpecModel} from "../../model";
import {CategoryTree} from "../category/CategoryConvertor";
import {Button, Col, Form, Input, message, Row, Select, Space, TreeSelect} from "antd";
import {getProductSpecListAPI} from "../../api/ProductSpecAPI";
import {FormFinishInfo} from "rc-field-form/lib/FormContext";

const formItemLayout = {
    labelCol: {
        xs: {span: 24},
        sm: {span: 4, offset: 2},
    },
    wrapperCol: {
        xs: {span: 24},
        sm: {span: 16},
    },
};
const tailFormItemLayout = {
    wrapperCol: {
        xs: {
            span: 24,
            offset: 0,
        },
        sm: {
            span: 20,
            offset: 5,
        },
    },
};

function ProductAddStep2(props: {
    productModel: ProductModel,
    onPreviousClick: (productModel: ProductModel) => void,
    onSubmit: (productModel: ProductModel) => void,
    categories: CategoryTree[],
    setProductModel: (productModel: ProductModel | undefined) => void
}) {
    const [form] = Form.useForm();
    const {productModel, setProductModel, categories} = props;
    const [productSpecs, setProductSpecs] = useState<Array<ProductSpecModel>>();
    useEffect(() => {
        getProductSpecListAPI({example: {categoryId: productModel?.category}}).then((result) => {
            const items = result.result?.items?.map(item => ({
                ...item, valueArray: Array.from(new Set(item.value?.split("\n")))
            }));
            setProductSpecs(items);
        }).catch((error) => {
            message.error(error.msgDetail);
        })
    }, [setProductSpecs, productModel]);


    function onPreviousClick() {
        console.log({...productModel, ...form.getFieldsValue()})
        props.onPreviousClick({...productModel, ...form.getFieldsValue()});
    }

    function onFinish(value: any) {
        console.log(value)
        const newProduct = {...productModel, ...form.getFieldsValue()}
    }

    function onCategoryChange(categoryId: number) {
        setProductModel({...productModel,specs:undefined, category: categoryId});
    }

    function onFormFinish(name: string, info: FormFinishInfo) {
        console.log(name, info)
    }

    function onSpecValueChange(changedFields: any, allFields:{[key:string]:string}) {
        console.log(allFields);
        setProductModel({...productModel,specs:allFields});
    }

    return (
        <div style={{
            marginTop: '50px',
            display: 'flex',
            flexDirection: 'column',
            overflow: 'auto',
            height: '100%',
            alignItems: 'center',
            width: '100%',

        }}>
            <Form.Provider
                onFormFinish={onFormFinish}
                // onFormChange={onSpecValueChange}
            >

                <Form
                    name={"cateogry"}
                    form={form}
                    style={{width: '100%'}}
                    {...formItemLayout}
                    layout="horizontal"
                >
                    <Form.Item label="商品分类" initialValue={productModel ? (productModel.category + "") : "0"}
                               name="category"
                               rules={[{required: true, message: '必须设置种类所属'}]}>
                        <TreeSelect notFoundContent={<div>数据加载错误,请检查网络</div>}
                                    treeData={categories}
                                    onChange={onCategoryChange}
                        />
                    </Form.Item>
                </Form>

                {(productSpecs?.length || 0) > 0 &&
                (
                    <Row
                        justify={'center'}
                        style={{width: '100%'}}
                    >
                        <Col

                            style={{backgroundColor:'#eeeeee', padding:"10px 20px", border:'1'}}
                        xs={{span:24}} sm={{span:14}}
                        >
                                <h3>产品规格</h3>
                        <Form
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
                            onValuesChange={onSpecValueChange}
                        >
                            {
                                productSpecs?.map(spec =>
                                    <Form.Item key={spec.id} name={spec.name} label={spec.name}
                                    >
                                        {spec.entryMethod === 'custom' ? <Input style={{flex: '1 0 0px'}}/> :
                                            <Select
                                                style={{flex: '1 0 0px'}} options={
                                                spec.valueArray?.map(val =>
                                                    ({label: val, value: val}))}
                                            />}
                                    </Form.Item>
                                )
                            }
                        </Form>
                        </Col>
                    </Row>
                )

                }
                <Form
                    name={"submit"}
                >
                    <Form.Item  {...tailFormItemLayout}>
                        <Space>
                            <Button type="primary" htmlType="submit">
                                提交
                            </Button>
                            <Button type="default" onClick={onPreviousClick}>
                                上一步
                            </Button>
                        </Space>
                    </Form.Item>
                </Form>
            </Form.Provider>
        </div>
    );
}

export default ProductAddStep2;