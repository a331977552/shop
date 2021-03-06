import React, {useState} from 'react';
import {ProductModel, SkuKeyVal} from "../../../model";
import {CategoryTree} from "../../category/CategoryConvertor";
import {Button, Form, message, Space, TreeSelect} from "antd";
import {FormFinishInfo} from "rc-field-form/lib/FormContext";
import AttrAddForm from "./AttrAddForm";
import SpecAddForm from "./SpecAddForm";
import DetailEditor from "../DetailEditor";
import {ContentState, convertToRaw, EditorState} from "draft-js";
import draftToHtml from "draftjs-to-html";
import {loadItem, saveItem} from "../../../services";
import htmlToDraft from "html-to-draftjs";
import {debounce} from "../../../util/TimerUtils";
import {productAddAPI} from "../../../api";
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

function ProductAddStep2(props: {
    onPreviousClick: () => void,
    categories: CategoryTree[],
    productModel: ProductModel,
    resetProductAddingPage:()=>void,
    updateProduct: (productModel: ProductModel|undefined) => void
}) {
    const [categoryForm] = Form.useForm();
    const [skuList, setSkuList] = useState<Array<SkuKeyVal>>();
    const {categories, updateProduct, productModel,resetProductAddingPage} = props;
    let onCategoryChangeFuncRef: () => void;
    let onCategoryChangeFuncSpecRef: () => void;
    const [editorState, setEditorState] = useState(() => {
        const html = loadItem("product_adding_detail") || "";
        const contentBlock = htmlToDraft(html);
        if (contentBlock) {
            const contentState = ContentState.createFromBlockArray(contentBlock.contentBlocks);
            return EditorState.createWithContent(contentState);
        }
        return EditorState.createEmpty()
    });
    const {categoryId} = productModel;

    function onPreviousClick() {
        props.updateProduct({
            ...productModel,
            ...categoryForm.getFieldsValue()
        });
        props.onPreviousClick();
    }

    function onCategoryChange(categoryId: number) {
        updateProduct({...productModel, categoryId: categoryId});
        onCategoryChangeFuncRef && onCategoryChangeFuncRef();
        onCategoryChangeFuncSpecRef && onCategoryChangeFuncSpecRef();
    }

    function onFormFinish(name: string, info: FormFinishInfo) {
        let emptyPriceOrStock = (skuList || []).some(row => !((row["price"] as string || "").trim()) || !((row["stock"] as string || "").trim()));
        if (emptyPriceOrStock) {
            message.error("sku??????????????????!", 3);
            return;
        }
        Promise.all([info.forms['category'].validateFields(), info.forms['attr'].validateFields()]).then(() => {
            const detailHtml = draftToHtml(convertToRaw(editorState.getCurrentContent()));
            const specsRequestData = info.forms["spec"]?.getFieldsValue()||{};

            const skuRequestData = transformSkuListToRequestData(skuList);

            const key = "product_add_key_";
            const requestData:ProductModel = assembleProductRequestData(productModel,detailHtml,specsRequestData,skuRequestData);
            productAddAPI(requestData).then(() => {

                //empty cache
                onCategoryChangeFuncRef && onCategoryChangeFuncRef();
                onCategoryChangeFuncSpecRef && onCategoryChangeFuncSpecRef();
                setEditorState(EditorState.createEmpty());
                resetProductAddingPage();
                message.success({content: false ? "????????????" : "????????????", key, duration: 1});
            }).catch((err) => {
                message.error({content: false ? "????????????,??????" : "????????????,??????:" + err.msgDetail?err.msgDetail:err, duration: 3, key});
            })
        }).catch((error) => {
            message.error(error, 3);
        })
    }

    function _setSkuList(data: Array<SkuKeyVal> | undefined) {
        setSkuList(data);
    }

    function _setEditorState(editorState: EditorState) {
        debounce(() => {
            let html = draftToHtml(convertToRaw(editorState.getCurrentContent()));
            saveItem("product_adding_detail", html);
        }, 2000);
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
                    <Form.Item label="????????????" initialValue={categoryId}
                               name="categoryId"
                               rules={[{required: true, message: '????????????????????????'}]}>
                        <TreeSelect notFoundContent={<div>??????????????????,???????????????</div>}
                                    treeData={categories}
                                    onChange={onCategoryChange}
                        />
                    </Form.Item>
                </Form>
                <AttrAddForm
                    dataSource={skuList}
                    setDataSource={_setSkuList}
                    categoryId={categoryId}
                    onCategoryChangeRef={(onCategoryChange: () => void) => {
                        onCategoryChangeFuncRef = onCategoryChange;
                    }}
                />
                <SpecAddForm
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

export default ProductAddStep2;