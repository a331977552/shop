import React, {useEffect, useState} from 'react';
import {ProductModel} from "../../model";
import {Button, Form, Input, InputNumber, message, Radio, Select, TreeSelect, Upload} from "antd";
import {LoadingOutlined, PlusOutlined} from '@ant-design/icons';

import {useAppDispatch, useAppSelector} from "../../store/hooks";
import {CategoryTree} from "../category/CategoryConvertor";
import {getBrandList, selectBrandReducer} from "../../store/slices/brandSlice";
import {RcFile, UploadChangeParam} from "antd/lib/upload/interface";
import {getTokenFromStorage} from "../../store/TokenConfig";
import {log} from "../../services";


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

function ProductAddStep1(props: {
                             productModel: ProductModel,
                             onNextClick: (productModel: ProductModel) => void,
                             setProductModel: (productModel: ProductModel | undefined) => void,
                             categories: CategoryTree[]
                         }
) {
    const [form] = Form.useForm();
    const {productModel, setProductModel} = props;
    const [imgUploading, setImgUploading] = useState(false);

    const dispatch = useAppDispatch();
    let brandState = useAppSelector(selectBrandReducer);
    const brandOptions = brandState.data?.items.map((item) => ({
        value: String(item.id),
        label: item.name
    }))
    useEffect(() => {
        if (!brandState.data) {
            dispatch(getBrandList());
        }
    }, [dispatch, brandState.data]);

    function onFinish(value: ProductModel) {
        props.onNextClick({...value, standardImg: productModel.standardImg});
    }

    function beforeUpload(file: RcFile) {
        const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
        if (!isJpgOrPng) {
            message.error('You can only upload JPG/PNG file!');
        }
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isLt2M) {
            message.error('Image must smaller than 2MB!');
        }
        return isJpgOrPng && isLt2M;
    }

    const handleChange = (info: UploadChangeParam) => {
        setImgUploading(info.file.status === 'uploading');
        if (info.file.status === 'done') {
            setProductModel({...productModel, standardImg: info.file.response.result.id});
            // setImage(info.file.response.result as ImgModel);
        } else if (info.file.status === 'error') {
            message.error(info.file.response.msgDetail, 3)
        }
    };

    function onReset() {
        setProductModel(undefined);
        form.resetFields();
    }

    function onChange(changedFields: ProductModel, allFields: ProductModel) {
        props.setProductModel({...productModel, ...allFields});
    }


    return (
        <div style={{
            marginTop: '50px',
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
                onReset={onReset}
                onValuesChange={onChange}
                scrollToFirstError={true}
            >

                <Form.Item label="标题" name="name"
                           rules={[{message: "商品标题不能为空", required: true}]}
                           initialValue={productModel?.name}
                >
                    <Input/>
                </Form.Item>

                <Form.Item label="副标题" name="subtitle"
                           rules={[{message: "商品副标题不能为空", required: true}]}
                           initialValue={productModel?.subtitle}

                >
                    <Input
                    />
                </Form.Item>
                <Form.Item label="商品分类"
                           name="category"
                           rules={[{required: true, message: '必须设置种类所属'}]}>
                    <TreeSelect notFoundContent={<div>数据加载错误,请检查网络</div>}
                                treeData={props.categories}
                    />
                </Form.Item>
                <Form.Item label="品牌" name={"brand"}
                           rules={[{required: true, message: '必须选择品牌'}]}>
                    <Select
                        allowClear={true}
                        options={brandOptions}
                        showSearch
                        optionFilterProp="children"
                    >
                    </Select>
                </Form.Item>
                <Form.Item label="货号" name={"itemNo"}
                >
                    <Input maxLength={32}/>
                </Form.Item>
                <Form.Item label="计量单位" name={"suffix"}>
                    <Input max={10}/>
                </Form.Item>

                <Form.Item label="描述" name={"description"} >
                    <Input />
                </Form.Item>
                <Form.Item label="上架状态" name={"status"} >
                    <Radio.Group >
                        <Radio.Button value={"ON_SALE"}>上架中</Radio.Button>
                        <Radio.Button value={"OUT_OF_ORDER"}>已下架</Radio.Button>
                    </Radio.Group>
                </Form.Item>
                <Form.Item label="图片"
                >

                    <Upload
                        listType="picture-card"
                        className="avatar-uploader"
                        showUploadList={false}
                        headers={{
                            "Authorization": "Bearer " + getTokenFromStorage()
                        }}
                        action="/api-gateway/img-service/api/img"
                        beforeUpload={beforeUpload}
                        onChange={handleChange}
                    >
                        {(productModel?.standardImg) ?
                            <img src={"/api-gateway/img-service/api/img/" + productModel.standardImg} alt="avatar"
                                 style={{width: '100%'}}/> :
                            <div>
                                {imgUploading ? <LoadingOutlined/> : <PlusOutlined/>}
                                <div style={{marginTop: 8}}>Upload</div>
                            </div>
                        }
                    </Upload>
                </Form.Item>


                <Form.Item label="排序" name={"sort"} >
                    <InputNumber min={0} max={9999}/>
                </Form.Item>


                <Form.Item label="重量" name={"weight"} >
                    <InputNumber  min={0} max={999999999}/>
                </Form.Item>

                <Form.Item label="价格" name={"price"}>
                    <InputNumber  min={0} max={999999999}/>
                </Form.Item>

                <Form.Item label="市场价格" name={"marketPrice"}>
                    <InputNumber  min={0} max={999999999}/>
                </Form.Item>


                <Form.Item  {...tailFormItemLayout}>
                    <Button type="primary" htmlType="submit">
                        下一步
                    </Button>
                    <Button style={{marginLeft:30}} htmlType={"reset"}>
                        重置
                    </Button>
                </Form.Item>
            </Form>
        </div>
    );
}

export default ProductAddStep1;