import React, {useState} from 'react';
import {ProductModel, SkuKeyVal} from "../../../model";
import {CategoryTree} from "../../category/CategoryConvertor";
import {Button, Form, message, Space, TreeSelect} from "antd";
import {FormFinishInfo} from "rc-field-form/lib/FormContext";
import AttrUpdateForm from "./AttrUpdateForm";
import SpecUpdateForm from "./SpecUpdateForm";
import DetailEditor from "../DetailEditor";
import {ContentState, convertToRaw, EditorState} from "draft-js";
import draftToHtml from "draftjs-to-html";
import htmlToDraft from "html-to-draftjs";
import { productUpdateAPI} from "../../../api";
import {assembleProductRequestData, transformSkuListToRequestData} from "../ProductCommon";

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

function ProductUpdateStep2(props: {
    moveToPreviousStep: () => void,
    categories: CategoryTree[],
    productModel: ProductModel,
    updateProduct: (productModel: ProductModel|undefined) => void
}) {
    const [categoryForm] = Form.useForm();
    const {categories, updateProduct, productModel} = props;

    const [skuList, setSkuList] = useState<Array<SkuKeyVal>|undefined>(()=>{
        return productModel.skuList?.map(sku=>({
            ...JSON.parse(sku.attribute),price:sku.price,stock:sku.stock,img: {id:sku.img,loading:false}
        }));
    });
    let onCategoryChangeFuncRef: () => void;
    let onCategoryChangeFuncSpecRef: () => void;
    const {categoryId} = productModel;

    const [editorState, setEditorState] = useState(() => {
        const contentBlock = htmlToDraft(productModel.detail||'');
        if (contentBlock) {
            const contentState = ContentState.createFromBlockArray(contentBlock.contentBlocks);
            return EditorState.createWithContent(contentState);
        }
        return EditorState.createEmpty()
    });

    function onPreviousClick() {
        updateProduct({
            ...productModel,
            ...categoryForm.getFieldsValue()
        });
        props.moveToPreviousStep();
    }

    function onCategoryChange(categoryId: number) {
        updateProduct({...productModel, categoryId: categoryId});
        onCategoryChangeFuncRef && onCategoryChangeFuncRef();
        onCategoryChangeFuncSpecRef && onCategoryChangeFuncSpecRef();
    }

    function onFormFinish(name: string, info: FormFinishInfo) {
        let emptyPriceOrStock = (skuList || []).some(row => !((String(row["price"]) || "").trim()) || !((String(row["stock"])|| "").trim()));
        if (emptyPriceOrStock) {
            message.error("sku??????????????????!", 3);
            return;
        }
        Promise.all([info.forms['category'].validateFields(), info.forms['attr'].validateFields()]).then(() => {
            const detailHtml = draftToHtml(convertToRaw(editorState.getCurrentContent()));
            const specsRequestData = info.forms["spec"]?.getFieldsValue()||{};
            const skuRequestData =transformSkuListToRequestData(skuList);
            const requestData:ProductModel = assembleProductRequestData(productModel,detailHtml,specsRequestData,skuRequestData)
            const key = "product_update_key_";
            productUpdateAPI(requestData).then(() => {
                message.success({content:  "????????????" , key, duration: 1});
            }).catch((err) => {
                console.log(err)
                message.error({content: "????????????,??????" + err.msgDetail?err.msgDetail:err, duration: 3, key});
            })
        }).catch((error) => {
            message.error(error, 3);
        })
    }

    function _setSkuList(data: Array<SkuKeyVal> | undefined) {
        setSkuList(data);
    }

    function _setEditorState(editorState: EditorState) {
        setEditorState(editorState);
    }

    return (
        <div style={{
            display: 'flex',
            flexDirection: 'column',
            overflow: 'auto',
            height: '100%',
            alignItems: 'center',
            width: '100%',

        }}>
            <Form.Provider
                onFormFinish={onFormFinish}
            >
                <Form
                    name={"category"}
                    form={categoryForm}
                    style={{width: '100%'}}
                    {...formItemLayout}
                    layout="horizontal"
                    preserve={false}
                >
                    <Form.Item label="????????????" initialValue={String(categoryId)}
                               name="categoryId"
                               rules={[{required: true, message: '????????????????????????'}]}>
                        <TreeSelect notFoundContent={<div>??????????????????,???????????????</div>}
                                    treeData={categories}
                                    onChange={onCategoryChange}
                        />
                    </Form.Item>
                </Form>
                <AttrUpdateForm
                    dataSource={skuList}
                    setDataSource={_setSkuList}
                    initAttrValues={productModel.skuList}
                    categoryId={categoryId}
                    onCategoryChangeRef={(onCategoryChange: () => void) => {
                        onCategoryChangeFuncRef = onCategoryChange;
                    }}
                />
                <SpecUpdateForm
                    initialSpecs={productModel.specs}
                    categoryId={categoryId}
                    onCategoryChangeFuncSpecRef={(onCategoryChange: () => void) => {
                        onCategoryChangeFuncSpecRef = onCategoryChange;
                    }}
                />
                <DetailEditor
                    editorState={editorState}
                    setEditorState={_setEditorState}
                />
                <Form
                    name={"submit"}
                    style={{marginTop: '20px'}}
                    preserve={false}
                >
                    <Form.Item>
                        <Space>
                            <Button type="primary" htmlType="submit">
                                ??????
                            </Button>
                            <Button type="default" onClick={onPreviousClick}>
                                ?????????
                            </Button>
                        </Space>
                    </Form.Item>
                </Form>
            </Form.Provider>
        </div>
    );
}

export default ProductUpdateStep2;