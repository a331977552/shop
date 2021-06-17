import React, {useState} from 'react';
import {Steps} from "antd";
import ProductAddStep1 from "./ProductAddStep1";
import ProductAddStep2 from "./ProductAddStep2";
import {Route, Switch, useHistory} from "react-router-dom";
import {ProductModel} from "../../model";

const {Step} = Steps;

function ProductAddPage() {
    const [currentStep, setCurrentStep] = useState(0);
    const [productModel, setProductModel] = useState<ProductModel|undefined>();
    function onNextClick(product:ProductModel){
        setProductModel(product);
        setCurrentStep(currentStep+1);
    }
    function onPreviousClick(){
        setCurrentStep(currentStep-1);
    }

    function onSubmit(productModel:ProductModel){
        setProductModel(productModel);
        console.log("onSubmit")
    }

    return (
        <div style={{width: '100%', display: 'flex', flexDirection: 'column', alignItems: 'center'}}>
            <div style={{width: '400px',marginTop:'20px'}}>
                <Steps current={currentStep}>
                    <Step title="商品信息录入"/>
                    <Step title="商品SKU录入"/>
                </Steps>
            </div>
            <div style={{width: '600px'}}>
                {
                    currentStep ===0 && <ProductAddStep1 productModel={productModel}  onNextClick={onNextClick}/>
                }
                {
                    currentStep === 1 && <ProductAddStep2 productModel={productModel}  onPreviousClick={onPreviousClick} onSubmit={onSubmit}/>
                }
            </div>
</div>
);
}

export default ProductAddPage;