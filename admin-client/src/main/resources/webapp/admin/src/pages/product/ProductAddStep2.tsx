import React, {} from 'react';
import { ProductModel} from "../../model";
import {CategoryTree} from "../category/CategoryConvertor";
import {Button, Form, Space, TreeSelect} from "antd";
import {FormFinishInfo} from "rc-field-form/lib/FormContext";
import AttrAddForm from "./AttrAddForm";
import SpecAddForm from "./SpecAddForm";

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
    const {productModel, setProductModel, categories} = props;

    function onPreviousClick() {
        props.onPreviousClick({
            ...productModel,
            ...categoryForm.getFieldsValue()
        });
    }


    function onCategoryChange(categoryId: number) {
        setProductModel({...productModel, specs: undefined,attrs:undefined, category: categoryId});
    }

    function onFormFinish(name: string, info: FormFinishInfo) {
        console.log(name, info)
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
                <AttrAddForm productModel={productModel} setProductModel={setProductModel}/>

                <SpecAddForm productModel={productModel} setProductModel={setProductModel}/>
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