import React, {useEffect, useState} from 'react';
import {InputNumber, message, Spin, Typography} from 'antd';
import {Form, Input, Button, Radio, TreeSelect} from 'antd';
import {useAppDispatch, useAppSelector} from "../../store/hooks";
import {
    getCategoryList, selectCategoryDataReducer, selectUITree,
} from "../../store/slices/cateogrySlice";
import {CategoryModel} from "../../model";
import {addCategoryAPI, updateCategoryAPI} from "../../store/api/CategoryAPI";
import {useParams} from "react-router-dom";
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


const CategoryUpdatePage = () => {
    const {cateID} = useParams<{ cateID: string }>();
    const category = (useAppSelector(selectCategoryDataReducer)?.
    items.find((item) => item.id === +cateID)) as  CategoryModel;
    const categories = useAppSelector(selectUITree);
    let dispatch = useAppDispatch();

    const onFinish = (values: any) => {
        values.parent = +values.parent;
        const key = "category_update_key";
        message.loading({content:"更新中...",key})
        updateCategoryAPI(values).then(response => {
            dispatch(getCategoryList({}));
            message.success({content:"更新成功",key})
        }).catch(reason => {
            message.error({content:"更新失败,原因:" +reason.errorMsg,key});
        })
    };

    return (
        <div style={{display: 'flex', flexDirection: 'column', alignItems: 'center', width: '100%'}}>
            <Title style={{marginBottom: '30px', marginTop: '10px'}}>种类更新</Title>
            <Form
                style={{width: '100%'}}
                {...formItemLayout}
                layout="horizontal"
                onFinish={onFinish}
            >
                <Form.Item name="id"
                           hidden={true}
                           rules={[{message: "种类名称不能为空", required: true}]}
                           initialValue={cateID}
                >
                    <Input value={cateID}/>
                </Form.Item>
                <Form.Item
                    label="种类名称" name="name"
                    rules={[{message: "种类名称不能为空", required: true}]}
                    initialValue={category.name}
                >
                    <Input/>
                </Form.Item>
                <Form.Item label="上级分类" name="parent" initialValue={category.parent + ""}>
                    <TreeSelect notFoundContent={<div>数据加载错误,请检查网络</div>}
                                treeData={categories}
                    />
                </Form.Item>
                <Form.Item label="分类描述" name="description" initialValue={category.description}
                           rules={[{max:32,message:'描述长度不可超过32'}]}
                >
                    <Input/>
                </Form.Item>
                <Form.Item label="关键字" name="keyword" initialValue={category?.keyword}>
                    <Input/>
                </Form.Item>
                <Form.Item label="后缀单位" name={"suffix"} initialValue={category?.suffix}>
                    <Input/>
                </Form.Item>

                <Form.Item label="是否显示" name="visible" initialValue={category.visible + ""}>
                    <Radio.Group>
                        <Radio.Button value="true">显示</Radio.Button>
                        <Radio.Button value="false">隐藏</Radio.Button>
                    </Radio.Group>
                </Form.Item>
                <Form.Item label="导航显示" name="navVisible" initialValue={category.navVisible}>
                    <Radio.Group>
                        <Radio.Button value="true">显示</Radio.Button>
                        <Radio.Button value="false">隐藏</Radio.Button>
                    </Radio.Group>
                </Form.Item>
                <Form.Item label="排序" name="priority" initialValue={category.priority}>
                    <InputNumber max={9999} min={0}/>
                </Form.Item>
                <Form.Item  {...tailFormItemLayout}>
                    <Button type="primary" htmlType="submit">
                        更新
                    </Button>
                </Form.Item>
            </Form>
        </div>
    );
};

export default CategoryUpdatePage;
