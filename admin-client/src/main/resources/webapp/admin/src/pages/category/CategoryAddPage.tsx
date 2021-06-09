import React, {useEffect, useState} from 'react';
import {InputNumber, message, TreeSelect, Typography} from 'antd';
import {Form, Input, Button, Radio} from 'antd';
import {useAppDispatch, useAppSelector} from "../../store/hooks";
import {
    getCategoryList, selectCategoryReducer,
} from "../../store/slices/cateogrySlice";
import {addCategoryAPI} from "../../store/api/CategoryAPI";
import {CategoryTree, convertToTreeStyle} from "./CategoryConvertor";

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
    const [categories, setCategories] = useState(new Array<CategoryTree>());
    const [form] = Form.useForm();

    const dispatch = useAppDispatch();
    const hierarchyCategories = useAppSelector(selectCategoryReducer);
    useEffect(() => {
        dispatch(getCategoryList({currentPage: 0, pageSize: 20}))
    }, [dispatch]);
    useEffect(() => {
       const topLevelTree = convertToTreeStyle(hierarchyCategories.categoryList.data?.items);
        setCategories(topLevelTree);
    }, [hierarchyCategories]);

    const onFinish = (values: any) => {
        console.log(values)
        values.parent = +values.parent;
        const hide = message.loading("添加中...",0)
        addCategoryAPI(values).then(response => {
            hide();
            dispatch(getCategoryList({currentPage: 0, pageSize: 20}))
            form.resetFields();
            message.success("添加成功")
        }).catch(reason => {
            hide();
            message.error("添加失败,原因:"+reason.errorMsg);
        });
    };


    return (
        <div style={{display: 'flex', flexDirection: 'column', alignItems: 'center', width: '100%'}}>
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
                <Form.Item label="父种类" name="parent" initialValue={"0"}>
                    <TreeSelect  notFoundContent={<div>数据加载错误,请检查网络</div>}
                                 treeData={categories}
                    />
                </Form.Item>
                <Form.Item label="是否显示" name="visible" initialValue={"true"}>
                    <Radio.Group>
                        <Radio.Button value="true">显示</Radio.Button>
                        <Radio.Button value="false">隐藏</Radio.Button>
                    </Radio.Group>
                </Form.Item>
                <Form.Item label="排序" name="priority" initialValue={0}>
                    <InputNumber max={9999} min={0}/>
                </Form.Item>
                <Form.Item label="后缀单位" name={"suffix"}>
                    <Input/>
                </Form.Item>
                <Form.Item label="keyword" name="keyword">
                    <Input/>
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
