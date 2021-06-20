import React, {useEffect, useState} from 'react';
import {ProductAttrModel, ProductModel, ProductSpecModel} from "../../model";
import {CategoryTree} from "../category/CategoryConvertor";
import {Button, Col, Form, Input, message, Row, Select, Space, TreeSelect} from "antd";
import {getProductSpecListAPI} from "../../api/ProductSpecAPI";
import {FormFinishInfo} from "rc-field-form/lib/FormContext";
import {getProductAttrListAPI} from "../../api/ProductAttrAPI";
import TextArea from "antd/es/input/TextArea";
import AttrAddForm from "./AttrAddForm";

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

function ProductAddStep2(props: {
    productModel: ProductModel,
    onPreviousClick: (productModel: ProductModel) => void,
    onSubmit: (productModel: ProductModel) => void,
    categories: CategoryTree[],
    setProductModel: (productModel: ProductModel | undefined) => void
}) {
    const [categoryForm] = Form.useForm();
    const [specForm] = Form.useForm();
    const {productModel, setProductModel, categories} = props;
    const [productSpecs, setProductSpecs] = useState<Array<ProductSpecModel>>();
    const [productAttrs, setProductAttrs] = useState<Array<ProductAttrModel>>();
    useEffect(() => {
        getProductSpecListAPI({example: {categoryId: productModel?.category}}).then((result) => {
            const items = result.result?.items?.map(item => ({
                ...item, valueArray: Array.from(new Set(item.value?.split("\n")))
            }));
            setProductSpecs(items);
        }).catch((error) => {
            message.error(error.msgDetail, 3);
        })
    }, [setProductSpecs, productModel?.category]);

    useEffect(() => {
        getProductAttrListAPI({example: {categoryId: productModel?.category}}).then((result) => {
            const items = result.result?.items;
            setProductAttrs(items);
        }).catch((error) => {
            message.error(error.msgDetail, 3);
        })
    }, [setProductAttrs, productModel?.category]);


    function onPreviousClick() {
        props.onPreviousClick({
            ...productModel,
            ...categoryForm.getFieldsValue(),
            specs: specForm.getFieldsValue()
        });
    }


    function onCategoryChange(categoryId: number) {
        console.log("onCategoryChange")
        setProductModel({...productModel, specs: undefined, category: categoryId});
    }

    function onFormFinish(name: string, info: FormFinishInfo) {
        console.log(name, info)
    }

    function onSpecValueChange(changedFields: any, allFields: { [key: string]: string | string[] }) {
        console.log("onSpecValueChange", allFields)

        setProductModel({...productModel, specs: allFields});
    }

    console.log(productModel)


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
                    form={categoryForm}
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
                {(productAttrs?.length || 0) > 0 &&
                    <AttrAddForm productAttrs={productAttrs as Array<ProductAttrModel>} productModel={productModel} setProductModel={setProductModel}/>
                }

                {(productSpecs?.length || 0) > 0 &&
                (
                    <Row
                        justify={'center'}
                        style={{width: '100%'}}
                    >
                        <Col

                            style={{backgroundColor: '#eeeeee', padding: "10px 20px", border: '1'}}
                            xs={{span: 24}} sm={{span: 14}}
                        >
                            <h3>产品规格</h3>
                            <Form
                                form={specForm}
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
                                                   initialValue={(productModel?.specs || {})[spec.name]}
                                        >
                                            {spec.entryMethod === 'custom' ? <Input style={{flex: '1 0 0px'}}/> :
                                                <Select
                                                    showArrow
                                                    allowClear={true}
                                                    {...spec.selectType === 'multiple' ? {mode: 'multiple'} : {}}
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
                    style={{marginTop: '20px'}}
                >
                    <Form.Item>
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