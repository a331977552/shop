import React, {useEffect, useState} from 'react';
import {InputNumber, message, TreeSelect, Typography} from 'antd';
import {Form, Input, Button, Radio} from 'antd';
import { useAppSelector} from "../../store/hooks";
import {
     selectUITree,
} from "../../store/slices/cateogrySlice";
import TextArea from "antd/es/input/TextArea";
import {RadioChangeEvent} from "antd/lib/radio/interface";
import {ProductAttrModel} from "../../model";
import {
    AddAttrAPI, getProductAttrAPI,
    updateAttrAPI
} from "../../store/api/ProductAttrAPI";
import {useParams} from "react-router-dom";
import {CategoryTree} from "../category/CategoryConvertor";
import CentredLoading from "../../components/CentredLoading";

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

function ProductAttrAddOrUpdatePage() {
    const [form] = Form.useForm();
    const {attrID} = useParams<{ attrID: string }>();
    const isUpdate = !!attrID;
    const [attrModel, setAttrModel] = useState<ProductAttrModel>();
    const [showEntryMethod, setShowEntryMethod] = useState(true);
    let cates = useAppSelector(selectUITree) as CategoryTree[];
    const categories = cates.slice(1);
    useEffect(() => {
        if (attrID) {
            getProductAttrAPI(+attrID).then(r => {
                setAttrModel(r.result);
                setShowEntryMethod(r.result?.entryMethod === 'selection')
            }).catch((error) => {
                message.error("获取产品参数错误,请检查网络")
            })
        }
    }, [attrID, setAttrModel]);


    function onFinish(model: ProductAttrModel) {
        const key = "attr_update";
        if (showEntryMethod){
            let values = model.value.split("\n");
            model.values = values.map(value => ({value:value}));
        }

        message.loading({content:isUpdate ? "更新中" : "添加中...", key});
        let promise;
        if (isUpdate) {
            promise = updateAttrAPI(model);
        } else {
            promise = AddAttrAPI(model).then(() => {
                form.resetFields();
            })
        }
        promise.then(() => {
            message.success({content:isUpdate ? "更新成功" : "添加成功",duration:1,key});
        }).catch((err) => {
            message.error({content:isUpdate ? "更新失败,原因" : "添加失败,原因:" + err.msgDetail,duration:3,key});
        })
    }

    function onEntryMethodChange(val: RadioChangeEvent) {
        setShowEntryMethod(val.target.value === 'selection')
    }

    if ((attrID && !attrModel) || !categories || (categories.length <= 1))
        return <CentredLoading/>
    return (
        <div style={{
            display: 'flex',
            flexDirection: 'column',
            overflow: 'auto',
            height: '100%',
            alignItems: 'center',
            width: '100%'
        }}>
            <Title level={2} style={{marginBottom: '30px', marginTop: '10px'}}>{attrID ? "商品属性更新" : "商品属性添加"}</Title>
            <Form
                form={form}
                style={{width: '100%'}}
                {...formItemLayout}
                layout="horizontal"
                onFinish={onFinish}
            >

                {attrModel && <Form.Item name={"id"} hidden={true} initialValue={attrModel?.id}
                >
                    <Input/>
                </Form.Item>}

                <Form.Item label="属性名称" name="name"
                           initialValue={attrModel?.name}
                           rules={[{message: "属性名称不能为空", required: true}]}
                >
                    <Input/>
                </Form.Item>
                <Form.Item label="选择类型" name="selectType" initialValue={attrModel?.selectType || "single"}>
                    <Radio.Group>
                        <Radio.Button value="single">单选</Radio.Button>
                        <Radio.Button value="multiple">多选</Radio.Button>
                    </Radio.Group>
                </Form.Item>
                <Form.Item label="录入方式" name="entryMethod" initialValue={attrModel?.entryMethod || "selection"}>
                    <Radio.Group onChange={onEntryMethodChange}>
                        <Radio.Button value="custom">手写录入</Radio.Button>
                        <Radio.Button value="selection">从列表中选择</Radio.Button>
                    </Radio.Group>
                </Form.Item>
                {showEntryMethod && <Form.Item initialValue={attrModel?.values.map(val => val.value).join("\n")} label="列表值" name="value"
                                               rules={[{required: true, message: '列表不能为空'}]}>
                    <TextArea placeholder={"例子:\r\n蓝色\r\n紫色\r\n红色"} rows={4}/>
                </Form.Item>}
                <Form.Item label="商品分类" initialValue={attrModel ? (attrModel.categoryId + ""):categories[0].value } name="categoryId"
                           rules={[{required: true, message: '必须设置种类所属'}]}>
                    <TreeSelect notFoundContent={<div>数据加载错误,请检查网络</div>}
                                treeData={categories}
                    />
                </Form.Item>
                <Form.Item label="排序" name={"sort"} initialValue={attrModel?.sort || 0}>
                    <InputNumber min={0} max={9999}/>
                </Form.Item>
                <Form.Item label="是否被检索" name="searchable"
                           initialValue={attrModel?(attrModel.searchable+""): "true"}>
                    <Radio.Group>
                        <Radio.Button value="true">是</Radio.Button>
                        <Radio.Button value="false">否</Radio.Button>
                    </Radio.Group>
                </Form.Item>
                <Form.Item label="输入类型" name="searchtype"
                           initialValue={(attrModel?.searchtype) || "normal"}>
                    <Radio.Group>
                        <Radio.Button value="normal">普通</Radio.Button>
                        <Radio.Button value="color">带图</Radio.Button>
                    </Radio.Group>
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

export default ProductAttrAddOrUpdatePage;