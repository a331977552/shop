import React, {useEffect, useState} from 'react';
import {InputNumber, message, Typography} from 'antd';
import {Form, Input, Button, Radio} from 'antd';
import {useAppSelector} from "../../store/hooks";

import {useParams} from "react-router-dom";
import CentredLoading from "../../components/CentredLoading";
import {BrandModel} from "../../model";
import {addBrandAPI, getBrandAPI, updateBrandAPI} from "../../api";
import {selectBrandList} from "../../store/slices/brandSlice";

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

function BrandAddOrUpdatePage() {
    const [form] = Form.useForm();
    const {bid} = useParams<{ bid: string }>();
    const isUpdate = !!bid;
    let brandModels = useAppSelector(selectBrandList);
    const [brandModel, setBrandModel] = useState<BrandModel | undefined>(brandModels?.find(({id}) => id === +bid));
    useEffect(() => {
        if (isUpdate && !brandModel) {
            getBrandAPI(bid).then((result) => {

                setBrandModel(result.result);
            }).catch((error) => {
                message.error("加载失败,请检查网络,原因:" + error.msgDetail, 3);
            })
        }
    }, [isUpdate, brandModel, bid, setBrandModel])


    function onFinish(value: BrandModel) {
        const key = "brand_message_update";
        message.loading({content: isUpdate ? "更新中" : "添加中...", key});
        let promise;
        if (isUpdate) {
            promise = updateBrandAPI(value);
        } else {
            promise = addBrandAPI(value).then(() => {
                form.resetFields();
            })
        }
        promise.then(() => {
            message.success({content: isUpdate ? "更新成功" : "添加成功", key, duration: 1});
        }).catch((err) => {
            message.error({content: isUpdate ? "更新失败,原因" : "添加失败,原因:" + err.msgDetail, duration: 3, key});
        })
    }

    if (isUpdate && !brandModel)
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
            <Title level={2} style={{marginBottom: '30px', marginTop: '10px'}}>{bid ? "商品参数更新" : "商品参数添加"}</Title>
            <Form
                form={form}
                style={{width: '100%'}}
                {...formItemLayout}
                layout="horizontal"
                onFinish={onFinish}
            >

                {brandModel && <Form.Item name={"id"} hidden={true} initialValue={brandModel?.id}
                >
                    <Input/>
                </Form.Item>}

                <Form.Item label="品牌名称" name="name"
                           initialValue={brandModel?.name}
                           rules={[{message: "品牌名称不能为空", required: true}]}
                >
                    <Input/>
                </Form.Item>
                <Form.Item label="品牌首字母" name="capitalLetter"
                           rules={[{max: 1, required: true, message: ''}]}
                           initialValue={brandModel?.capitalLetter}>
                    <Input/>
                </Form.Item>

                <Form.Item label="排序" name={"priority"} initialValue={brandModel?.priority || 0}>
                    <InputNumber min={0} max={9999}/>
                </Form.Item>
                <Form.Item label="品牌生产商" name="isManufacturer"
                           initialValue={brandModel ? String(brandModel.isManufacturer) : "true"}>
                    <Radio.Group>
                        <Radio.Button value="true">是</Radio.Button>
                        <Radio.Button value="false">否</Radio.Button>
                    </Radio.Group>
                </Form.Item>
                <Form.Item label="显示" name="visible"
                           initialValue={brandModel ? String(brandModel.visible) : "true"}>
                    <Radio.Group>
                        <Radio.Button value="true">是</Radio.Button>
                        <Radio.Button value="false">否</Radio.Button>
                    </Radio.Group>
                </Form.Item>
                <Form.Item label="品牌描述" name="description"
                           initialValue={brandModel && String(brandModel.description)}>
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
}

export default BrandAddOrUpdatePage;
