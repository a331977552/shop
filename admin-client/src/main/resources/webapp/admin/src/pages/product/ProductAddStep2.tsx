import React, {useState} from 'react';
import {ProductModel, SkuKeyVal} from "../../model";
import {CategoryTree} from "../category/CategoryConvertor";
import {Button, Form, message, Space, TreeSelect} from "antd";
import {FormFinishInfo} from "rc-field-form/lib/FormContext";
import AttrAddForm from "./AttrAddForm";
import SpecAddForm from "./SpecAddForm";
import DetailEditor from "./DetailEditor";
import {ContentState, convertToRaw, EditorState} from "draft-js";
import draftToHtml from "draftjs-to-html";
import {loadItem, saveItem} from "../../services";
import htmlToDraft from "html-to-draftjs";
import {debounce} from "../../util/TimerUtils";

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
    onPreviousClick: (productModel: ProductModel) => void,
    categories: CategoryTree[],
    productModel: ProductModel,
    updateProduct: (productModel: ProductModel) => void
}) {
    const [categoryForm] = Form.useForm();
    const [skuList, setSkuList] = useState<Array<SkuKeyVal>>();
    const [editorState, setEditorState] = useState(()=>{
        const html = loadItem("product_adding_detail") || "";
        console.log("init detail from cache")
        const contentBlock = htmlToDraft(html);
        if (contentBlock) {
            const contentState = ContentState.createFromBlockArray(contentBlock.contentBlocks);
            return EditorState.createWithContent(contentState);
        }
        return EditorState.createEmpty()
    });
    const {categories, updateProduct, productModel} = props;
    const {category} = productModel;

    function onPreviousClick() {
        props.onPreviousClick({
            ...productModel,
            ...categoryForm.getFieldsValue()
        });
    }


    function onCategoryChange(categoryId: number) {
        updateProduct({...productModel, category: categoryId});
    }

    function onFormFinish(name: string, info: FormFinishInfo) {
        let emptyPriceOrStock = (skuList || []).some(row => !((row["price"] as string || "").trim()) || !((row["stock"] as string || "").trim()));
        if (emptyPriceOrStock) {
            message.error("sku列表不能为空!", 3);
            return;
        }
        let html = draftToHtml(convertToRaw(editorState.getCurrentContent()));
        console.log(name, info, skuList, html);
    }

    function _setSkuList(data: Array<SkuKeyVal> | undefined) {
        setSkuList(data);
    }

    function _setEditorState(editorState: EditorState) {
        debounce(()=>{
            let html = draftToHtml(convertToRaw(editorState.getCurrentContent()));
            saveItem("product_adding_detail",html);
        },2000);
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
                    name={"cateogry"}
                    form={categoryForm}
                    style={{width: '100%'}}
                    {...formItemLayout}
                    layout="horizontal"
                    preserve={false}
                >
                    <Form.Item label="商品分类" initialValue={category}
                               name="category"
                               rules={[{required: true, message: '必须设置种类所属'}]}>
                        <TreeSelect notFoundContent={<div>数据加载错误,请检查网络</div>}
                                    treeData={categories}
                                    onChange={onCategoryChange}
                        />
                    </Form.Item>
                </Form>
                <AttrAddForm
                    dataSource={skuList}
                    setDataSource={_setSkuList}
                    category={category}
                />
                <SpecAddForm category={category}/>
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
                                提交
                            </Button>
                            <Button type="default" onClick={onPreviousClick}>
                                上一步
                            </Button>
                        </Space>
                    </Form.Item>
                </Form>
            </Form.Provider>
        </div>
    );
}

export default ProductAddStep2;