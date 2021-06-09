import React, {useEffect, useState} from 'react';
import {InputNumber, message, Spin, Typography} from 'antd';
import {Form, Input, Button, Radio, TreeSelect} from 'antd';
import {useAppDispatch, useAppSelector} from "../../store/hooks";
import {
    getCategoryList, selectCategoryDataReducer,
} from "../../store/slices/cateogrySlice";
import {CategoryModel} from "../../model";
import {addCategoryAPI, updateCategoryAPI} from "../../store/api/CategoryAPI";
import {useParams} from "react-router-dom";
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


const CategoryUpdatePage = () => {
    const {cateID} = useParams<{ cateID: string }>();

    const [categories, setCategories] = useState(new Array<CategoryTree>());
    const [category, setCategory] = useState<CategoryModel>();
    const hierarchyCategories = useAppSelector(selectCategoryDataReducer);
    let dispatch = useAppDispatch();
    useEffect(() => {
        //user can directly access this url without initializing category list
        dispatch(getCategoryList(null));
    }, [dispatch]);

    useEffect(() => {
        if (hierarchyCategories) {
            const topLevelTree = convertToTreeStyle(hierarchyCategories.items, +cateID)
            setCategories(topLevelTree);
            let find = hierarchyCategories.items.find((item) => item.id === +cateID);
            setCategory(find);
        }
    }, [hierarchyCategories, setCategories, setCategory, cateID]);
    const onFinish = (values: any) => {
        values.parent = +values.parent;
        const hide = message.loading("更新中...", 0)
        updateCategoryAPI(values).then(response => {
            dispatch(getCategoryList({currentPage: 0, pageSize: 20}))
            message.success("更新成功")
        }).catch(reason => {
            message.error("更新失败,原因:" + reason.errorMsg);
        }).finally(() => {
            hide();
        });
    };
    if (category == null) {
        return <Spin spinning={true}/>
    }
    console.log(categories, category)
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
                <Form.Item label="父种类" name="parent" initialValue={category.parent + ""}>
                    <TreeSelect notFoundContent={<div>数据加载错误,请检查网络</div>}
                                treeData={categories}
                    />
                </Form.Item>
                <Form.Item label="是否显示" name="visible" initialValue={category.visible + ""}>
                    <Radio.Group>
                        <Radio.Button value="true">显示</Radio.Button>
                        <Radio.Button value="false">隐藏</Radio.Button>
                    </Radio.Group>
                </Form.Item>
                <Form.Item label="排序" name="priority" initialValue={category.priority}>
                    <InputNumber max={9999} min={0}/>
                </Form.Item>
                <Form.Item label="后缀单位" name={"suffix"} initialValue={category?.suffix}>
                    <Input/>
                </Form.Item>
                <Form.Item label="keyword" name="keyword" initialValue={category?.keyword}>
                    <Input/>
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
