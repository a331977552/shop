import React, {useState} from 'react';
import {Col, Row, Steps} from "antd";
import ProductAddStep1 from "./ProductAddStep1";
import ProductAddStep2 from "./ProductAddStep2";
import {ProductModel} from "../../model";
import {useAppSelector} from "../../store/hooks";
import {selectUITree} from "../../store/slices/cateogrySlice";
import {CategoryTree} from "../category/CategoryConvertor";
import {loadItem, removeItem, saveItem} from "../../services";
import {debounce} from "../../util/TimerUtils";

const {Step} = Steps;

function ProductAddPage() {

    const storageProductItem = loadItem("product_adding");
    const productStep: { currentStep: number, product?: ProductModel } = storageProductItem ? (JSON.parse(storageProductItem)) : {currentStep: 0,
    product:{status:'ON_SALE'}};

    const [currentStep, setCurrentStep] = useState(productStep.currentStep||0);
    const [productModel, setProductModel] = useState<ProductModel | undefined>(productStep.product);

    let categories = (useAppSelector(selectUITree) as CategoryTree[]).slice(1);

    function saveProduct(currentStep: number, product: ProductModel|undefined) {
        debounce(()=>{
            if (product){
                saveItem("product_adding", JSON.stringify({
                    currentStep,
                    product
                }));
            }else {
                removeItem("product_adding")
            }
        },2000);

    }

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

    function onSubmit(productModel: ProductModel) {
        setProductModel(productModel);
        // if (success){
        //     removeItem("product_adding");
        // }
        console.log("onSubmit")
    }

    function updateProduct(product: ProductModel|undefined) {
        saveProduct(currentStep, product)
        setProductModel(product);
    }

    return (
        <div style={{width: '100%', display: 'flex', flexDirection: 'column', alignItems: 'center'}}>
            <div style={{width: '400px', marginTop: '20px'}}>
                <Steps current={currentStep}>
                    <Step title="商品信息录入"/>
                    <Step title="商品SKU录入"/>
                </Steps>
            </div>
            <Row justify={"center"} style={{width: '100%'}}>
                <Col
                    xs={{span: 24}}
                    sm={{span: 20}}
                    md={{span: 20}}
                    lg={{span: 14}}
                >
                    {
                        currentStep === 0 &&
                        <ProductAddStep1 categories={categories}
                                         setProductModel={updateProduct}
                                         productModel={productModel as ProductModel} onNextClick={onNextClick}/>
                    }
                    {
                        currentStep === 1 && <ProductAddStep2 setProductModel={updateProduct} categories={categories}
                                                              productModel={productModel as ProductModel}
                                                              onPreviousClick={onPreviousClick} onSubmit={onSubmit}/>
                    }
                </Col>
            </Row>
        </div>
    );
}

export default ProductAddPage;