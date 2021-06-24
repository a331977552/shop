import React, {useEffect} from 'react';
import {ProductModel} from "../../../model";
import {Button, Form, FormInstance, Input, InputNumber, message, Radio, Select, Upload} from "antd";
import {useAppDispatch, useAppSelector} from "../../../store/hooks";
import {CategoryTree} from "../../category/CategoryConvertor";
import {getBrandList, selectBrandReducer} from "../../../store/slices/brandSlice";
import ImageUpload from "../ImageUpload";


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
const tailFormItemLayout = {
    wrapperCol: {
        xs: {
            span: 24,
            offset: 0,
        },
        sm: {
            span: 20,
            offset: 6,
        },
    },
};

function ProductUpdateStep1({productModel, updateProduct, form,moveToNextStep} : {
                             form: FormInstance,
                             productModel: ProductModel,
                             moveToNextStep: () => void,
                             updateProduct: (productModel: ProductModel | undefined) => void,
                             categories: CategoryTree[]
                         }
) {
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
        if (!productModel.standardImg){
            message.error("商品图片不能为空");
            return;
        }
        updateProduct({...productModel, ...value, standardImg: productModel.standardImg})
        moveToNextStep();
    }



    useEffect(() => {
        if (!productModel) {
            form.resetFields();
        }
    }, [productModel, form]);

    function onReset() {
        updateProduct(undefined);
    }

    function onValuesChange(changedFields: ProductModel, allFields: ProductModel) {
        updateProduct({...productModel, ...allFields});
    }

    const onImageUploadFinished = (id:string) => {
        updateProduct({...productModel, standardImg: id});
    };

    return (
        <div style={{
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
                onValuesChange={onValuesChange}
                scrollToFirstError={true}
                preserve={false}
            >
                <Form.Item label="标题" name="id"
                           initialValue={productModel?.id}
                           hasFeedback={true}
                           hidden={true}
                >
                    <Input/></Form.Item>
                <Form.Item label="标题" name="name"
                           rules={[{message: "商品标题不能为空", required: true}]}
                           initialValue={productModel?.name}
                           hasFeedback={true}
                >
                    <Input/>
                </Form.Item>

                <Form.Item label="副标题" name="subtitle"
                           rules={[{message: "商品副标题不能为空", required: true}]}
                           initialValue={productModel?.subtitle}
                           hasFeedback={true}
                >
                    <Input
                    />
                </Form.Item>

                <Form.Item label="品牌" name={"brand"}
                           rules={[{required: true, message: '必须选择品牌'}]}
                           initialValue={String(productModel?.brand)}
                           hasFeedback={true}

                >
                    <Select
                        allowClear={true}
                        options={brandOptions}
                        showSearch
                        optionFilterProp="children"
                    >
                    </Select>
                </Form.Item>
                <Form.Item label="货号" name={"itemNo"}
                           initialValue={productModel?.itemNo}
                           hasFeedback={true}
                >
                    <Input maxLength={32}/>
                </Form.Item>
                <Form.Item label="计量单位" name={"suffix"}
                           initialValue={productModel?.itemNo}
                           hasFeedback={true}
                >
                    <Input max={10}/>
                </Form.Item>

                <Form.Item label="描述" name={"description"}
                           initialValue={productModel?.description}
                           hasFeedback={true}
                >
                    <Input/>
                </Form.Item>
                <Form.Item label="上架状态" name={"status"}
                           initialValue={productModel?.status || "ON_SALE"}
                           hasFeedback={true}
                >
                    <Radio.Group>
                        <Radio.Button value={"ON_SALE"}>上架中</Radio.Button>
                        <Radio.Button value={"OUT_OF_ORDER"}>已下架</Radio.Button>
                    </Radio.Group>
                </Form.Item>
                <Form.Item label="图片"
                           required={true}
                >

                    <ImageUpload onUploadFinished={onImageUploadFinished} img={productModel?.standardImg}/>
                </Form.Item>

                <Form.Item label="排序" name={"priority"}
                           initialValue={productModel?.priority || 0}
                           hasFeedback={true}
                           rules={[{required: true, message: "排序不能为空"}]}
                >
                    <InputNumber min={0} max={9999}/>
                </Form.Item>

                <Form.Item label="重量" name={"weight"}
                           initialValue={productModel?.weight}
                           hasFeedback={true}
                >
                    <InputNumber min={0} max={999999999}/>
                </Form.Item>

                <Form.Item label="价格" name={"price"}
                           initialValue={productModel?.price}
                           hasFeedback={true}
                >
                    <InputNumber min={0} max={999999999}/>
                </Form.Item>

                <Form.Item label="市场价格" name={"marketPrice"}
                           initialValue={productModel?.marketPrice}
                           hasFeedback={true}
                >
                    <InputNumber min={0} max={999999999}/>
                </Form.Item>

                <Form.Item  {...tailFormItemLayout}>
                    <Button type="primary" htmlType="submit">
                        下一步
                    </Button>
                    <Button style={{marginLeft: 30}} htmlType={"reset"}>
                        重置
                    </Button>
                </Form.Item>
            </Form>
        </div>
    );
}

export default ProductUpdateStep1;