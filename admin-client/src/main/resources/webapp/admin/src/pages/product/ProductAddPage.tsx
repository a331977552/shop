import React, {useEffect, useState} from 'react';
import {Col, Form, Row, Steps} from "antd";
import ProductAddStep1 from "./ProductAddStep1";
import ProductAddStep2 from "./ProductAddStep2";
import {ProductModel} from "../../model";
import {useAppSelector} from "../../store/hooks";
import {selectUITree} from "../../store/slices/cateogrySlice";
import {CategoryTree} from "../category/CategoryConvertor";
import {loadItem, removeItem, saveItem} from "../../services";
import {debounce} from "../../util/TimerUtils";

const {Step} = Steps;

function saveProduct(currentStep: number, product: ProductModel | undefined) {
    debounce(() => {
        if (product) {
            saveItem("product_adding_product", JSON.stringify({
                currentStep,
                product
            }));
        } else {
            removeItem("product_adding_product")
        }
    }, 2000);
}
function loadProduct(){
    const storageProductItem = loadItem("product_adding_product");
    const productStep: { currentStep: number, product?: ProductModel } = storageProductItem ? (JSON.parse(storageProductItem)) : {
        currentStep: 0,
        product: {status: 'ON_SALE'}
    };
    return productStep;
}
function ProductAddPage() {

    const [productForm] = Form.useForm();
    const [currentStep, setCurrentStep] = useState(()=>{
        return loadProduct().currentStep;
    });
    const [productModel, setProductModel] = useState<ProductModel | undefined>(()=>{
        return loadProduct().product;
    });
    const categories = (useAppSelector(selectUITree) as CategoryTree[]).slice(1);

    function onNextClick(product: ProductModel) {
        setProductModel(product);
        saveProduct(currentStep + 1, product)
        setCurrentStep(currentStep + 1);
    }

    function onPreviousClick(product: ProductModel) {
        setProductModel(product);
        saveProduct(currentStep - 1, product)
        setCurrentStep(currentStep - 1);
    }

    function updateProduct(product: ProductModel | undefined) {
        console.log(product,currentStep)
        saveProduct(currentStep, product)
        setProductModel(product);
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
                                          productModel={productModel as ProductModel}
                                         onPreviousClick={onPreviousClick} />
                    }
                </Col>
            </Row>
        </div>
    );
}

export default ProductAddPage;