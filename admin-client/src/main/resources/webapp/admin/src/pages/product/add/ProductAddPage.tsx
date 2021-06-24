import React, { useState} from 'react';
import {Col, Form, Row, Steps} from "antd";
import ProductAddStep1 from "./ProductAddStep1";
import ProductAddStep2 from "./ProductAddStep2";
import {ProductModel} from "../../../model";
import {useAppSelector} from "../../../store/hooks";
import {selectUITree} from "../../../store/slices/cateogrySlice";
import {CategoryTree} from "../../category/CategoryConvertor";
import {loadItem, removeItem, saveItem} from "../../../services";
import {debounce} from "../../../util/TimerUtils";

const {Step} = Steps;

function saveProduct(product: ProductModel | undefined) {
    debounce(() => {
        if (product) {
            saveItem("product_adding_product", JSON.stringify(product));
        } else {
            removeItem("product_adding_product")
        }
    }, 2000);
}
function saveStep(step:number|undefined) {
    debounce(() => {
        if (step) {
            saveItem("product_adding_step", JSON.stringify(step));
        } else {
            removeItem("product_adding_step")
        }
    }, 2000);
}
function loadStep():number {
    return Number(loadItem("product_adding_step")) || 0;
}
function loadProduct():ProductModel{
    const storageProductItem = loadItem("product_adding_product");
    return storageProductItem ? (JSON.parse(storageProductItem)) :{status: 'ON_SALE',priority:0};
}
function ProductAddPage() {
    const [productForm] = Form.useForm();
    const [currentStep, setCurrentStep] = useState(()=>loadStep());
    const [productModel, setProductModel] = useState<ProductModel | undefined>(()=> loadProduct());
    const categories = (useAppSelector(selectUITree) as CategoryTree[]).slice(1);

    function onNextClick() {
        saveStep(currentStep+1);
        setCurrentStep(currentStep + 1);
    }

    function onPreviousClick() {
        saveStep(currentStep-1);
        setCurrentStep(currentStep - 1);
    }

    function updateProduct(product: ProductModel | undefined) {
        saveProduct(product)
        setProductModel(product);
    }

    function resetProductAddingPage() {
        saveProduct({status: 'ON_SALE',priority:0} as ProductModel)
        saveStep(0);
        setCurrentStep(0);
        setProductModel({status: 'ON_SALE',priority:0} as ProductModel);
    }

    return (
        <div style={{width: '100%', display: 'flex', flexDirection: 'column', alignItems: 'center'}}>
            <div style={{width: '400px', marginTop: '20px'}}>
                <Steps
                    current={currentStep}>
                    <Step title="商品信息录入"/>
                    <Step title="商品SKU录入"/>
                </Steps>
            </div>
            <Row justify={"center"} style={{width: '100%', marginTop: '50px'}}>
                <Col
                    xs={{span: 24}}
                    sm={{span: 20}}
                    md={{span: 20}}
                    lg={{span: 14}}
                >
                    {
                        currentStep === 0 &&
                        <ProductAddStep1 form={productForm} categories={categories}
                                         updateProduct={updateProduct}
                                         productModel={productModel as ProductModel} onNextClick={onNextClick}/>
                    }
                    {
                        currentStep === 1 &&
                        <ProductAddStep2  categories={categories}
                                          updateProduct={updateProduct}
                                          resetProductAddingPage={resetProductAddingPage}
                                          productModel={productModel as ProductModel}
                                          onPreviousClick={onPreviousClick} />
                    }
                </Col>
            </Row>
        </div>
    );
}

export default ProductAddPage;