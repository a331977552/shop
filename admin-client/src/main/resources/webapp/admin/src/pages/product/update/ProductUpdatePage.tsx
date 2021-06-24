import React, {useEffect, useState} from 'react';
import {Col, Form, Row, Steps} from "antd";
import ProductUpdateStep1 from "./ProductUpdateStep1";
import ProductUpdateStep2 from "./ProductUpdateStep2";
import {useAppSelector} from "../../../store/hooks";
import {selectUITree} from "../../../store/slices/cateogrySlice";
import {CategoryTree} from "../../category/CategoryConvertor";
import {useParams} from "react-router-dom";
import {ProductModel, Status} from "../../../model";
import {getProductAPI} from "../../../api";
import StatusView from "../../../components/StatusView";

const {Step} = Steps;


function ProductUpdatePage() {
    const {pid} = useParams<{ pid: string }>();
    const [productForm] = Form.useForm();
    const [currentStep, setCurrentStep] = useState(0);
    const [status, setStatus] = useState<Status>('loading');
    const [errorMsg, setErrorMsg] = useState<Status>();
    const [productModel, setProductModel] = useState<ProductModel | undefined>();
    const categories = (useAppSelector(selectUITree) as CategoryTree[]).slice(1);

    function loadProduct() {
        getProductAPI(pid).then((result) => {
            setProductModel(result.result);
            setStatus("finished");
        }).catch((error) => {
            setErrorMsg(error.msgDetail);
            setStatus("error");
        });

    }

    useEffect(loadProduct, [setProductModel, pid,setStatus,setErrorMsg]);

    function moveToNext() {
        setCurrentStep(currentStep + 1);
    }

    function moveToPrevious() {
        setCurrentStep(currentStep - 1);
    }

    function updateProduct(product: ProductModel | undefined) {
        setProductModel(product);
    }

    return (
        <StatusView status={status} retry={loadProduct} errorMsg={errorMsg}>
            <div style={{width: '100%', display: 'flex', flexDirection: 'column', alignItems: 'center'}}>
                <div style={{width: '400px', marginTop: '20px'}}>
                    <Steps
                        current={currentStep}>
                        <Step title="商品信息更新"/>
                        <Step title="商品SKU更新"/>
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
                            <ProductUpdateStep1 form={productForm} categories={categories}
                                                updateProduct={updateProduct}
                                                productModel={productModel as ProductModel} moveToNextStep={moveToNext}/>
                        }
                        {
                            currentStep === 1 &&
                            <ProductUpdateStep2 categories={categories}
                                                updateProduct={updateProduct}
                                                productModel={productModel as ProductModel}
                                                moveToPreviousStep={moveToPrevious}/>
                        }
                    </Col>
                </Row>
            </div>
        </StatusView>
    );
}

export default ProductUpdatePage;