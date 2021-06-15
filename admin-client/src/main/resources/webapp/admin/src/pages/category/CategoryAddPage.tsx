import React, {useEffect, useState} from 'react';
import {InputNumber, message, TreeSelect, Typography} from 'antd';
import {Form, Input, Button, Radio} from 'antd';
import {useAppDispatch, useAppSelector} from "../../store/hooks";
import {
    getCategoryList, selectCategoryReducer, selectUITree,
} from "../../store/slices/cateogrySlice";
import {addCategoryAPI} from "../../store/api/CategoryAPI";
import {CategoryTree, convertToUITree} from "./CategoryConvertor";

const {Title} = Typography;

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


const CategoryAddPage = () => {
    const dispatch = useAppDispatch();

    const [form] = Form.useForm();
    const categories = useAppSelector(selectUITree);


    const onFinish = (values: any) => {
        values.parent = +values.parent;
        const key= "category_add_key";
        message.loading({content:"添加中...",key})
        addCategoryAPI(values).then(response => {
            dispatch(getCategoryList({}))
            form.resetFields();
            message.success({content:"添加成功",key})
        }).catch(reason => {
            message.error({content:"添加失败,原因:"+reason.errorMsg,key});
        });
    };


    return (
        <div style={{display: 'flex', flexDirection: 'column', alignItems: 'center', overflow:'auto', width: '100%'}}>
            <Title style={{marginBottom: '30px', marginTop: '10px'}}>种类添加</Title>
            <Form
                form={form}
                style={{width: '100%'}}
                {...formItemLayout}
                layout="horizontal"
                onFinish={onFinish}
            >
                <Form.Item label="种类名称" name="name"
                           rules={[{message: "种类名称不能为空", required: true}]}
                >
                    <Input/>
                </Form.Item>
                <Form.Item label="上级分类" name="parent" initialValue={"0"}>
                    <TreeSelect  notFoundContent={<div>数据加载错误,请检查网络</div>}
                                 treeData={categories}
                    />
                </Form.Item>

                <Form.Item label="后缀单位" name={"suffix"}>
                    <Input/>
                </Form.Item>
                <Form.Item label="关键字" name="keyword">
                    <Input/>
                </Form.Item>
                <Form.Item label="分类描述" name="description"
                rules={[{max:32,message:'描述长度不可超过32'}]}
                >
                    <Input/>
                </Form.Item>
                <Form.Item label="是否显示" name="visible" initialValue={"true"}>
                    <Radio.Group>
                        <Radio.Button value="true">显示</Radio.Button>
                        <Radio.Button value="false">隐藏</Radio.Button>
                    </Radio.Group>
                </Form.Item>
                <Form.Item label="导航显示" name="navVisible" initialValue={"true"}>
                    <Radio.Group>
                        <Radio.Button value="true">显示</Radio.Button>
                        <Radio.Button value="false">隐藏</Radio.Button>
                    </Radio.Group>
                </Form.Item>
                <Form.Item label="排序" name="priority" initialValue={0}>
                    <InputNumber max={9999} min={0}/>
                </Form.Item>
                <Form.Item  {...tailFormItemLayout}>
                    <Button type="primary" htmlType="submit">
                        Submit
                    </Button>
                </Form.Item>
            </Form>
        </div>
    );
};

export default CategoryAddPage;
