import React from 'react';
import {ProductModel} from "../../model";
import {Button, Form, Input, InputNumber, TreeSelect} from "antd";
import {useAppSelector} from "../../store/hooks";
import {selectUITree} from "../../store/slices/cateogrySlice";
import {CategoryTree} from "../category/CategoryConvertor";


const formItemLayout = {
    labelCol: {
        xs: {span: 24},
        sm: {span: 4, offset: 1},
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
function ProductAddStep1(props:{productModel:ProductModel|undefined,onNextClick:(productModel:ProductModel)=>void}) {
    const [form] = Form.useForm();
    const {productModel} = props;
    let categories = useAppSelector(selectUITree) as CategoryTree[];


    function onFinish(value: ProductModel) {
        props.onNextClick(value);
    }

    return (
        <div style={{
            marginTop:'50px',
            display: 'flex',
            flexDirection: 'column',
            overflow: 'auto',
            height: '100%',
            alignItems: 'center',
            width: '100%'
        }}>

            <Form
                form={form}
                style={{width: '100%'}}
                {...formItemLayout}
                layout="horizontal"
                onFinish={onFinish}
            >


                <Form.Item label="规格名称" name="name"
                           initialValue={productModel?.name}
                           rules={[{message: "规格名称不能为空", required: true}]}
                >
                    <Input/>
                </Form.Item>
                <Form.Item label="商品分类" initialValue={productModel ? (productModel.category + "") : "0"}
                           name="categoryId"
                           rules={[{required: true, message: '必须设置种类所属'}]}>
                    <TreeSelect notFoundContent={<div>数据加载错误,请检查网络</div>}
                                treeData={categories}
                    />
                </Form.Item>
                <Form.Item label="排序" name={"sort"} initialValue={productModel?.priority || 0}>
                    <InputNumber min={0} max={9999}/>
                </Form.Item>

                <Form.Item  {...tailFormItemLayout}>
                    <Button type="primary" htmlType="submit">
                        Submit
                    </Button>
                </Form.Item>
            </Form>
        </div>
       );
}

export default ProductAddStep1;