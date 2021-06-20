import React, {useEffect, useState} from 'react';
import {ProductModel, ProductSpecModel} from "../../model";
import {Col, Form, Input, message, Row, Select} from "antd";
import {getProductSpecListAPI} from "../../api/ProductSpecAPI";

function SpecAddForm(
    props: {
        setProductModel: (productModel: ProductModel | undefined) => void,
        productModel: ProductModel
    }
) {
    const {setProductModel, productModel} = props;
    const [productSpecs, setProductSpecs] = useState<Array<ProductSpecModel>>();
    useEffect(() => {
        getProductSpecListAPI({example: {categoryId: productModel?.category}}).then((result) => {
            const items = result.result?.items?.map(item => ({
                ...item, valueArray: Array.from(new Set(item.value?.split("\n")))
            }));
            setProductSpecs(items);
        }).catch((error) => {
            message.error(error.msgDetail, 3);
        })
    }, [setProductSpecs, productModel?.category]);

    function onSpecValueChange(changedFields: any, allFields: { [key: string]: string[] }) {
        setProductModel({...productModel, specs: allFields});
    }

    return (
        ((productSpecs?.length || 0) === 0) ?null: <Row
            justify={'center'}
            style={{width: '100%'}}>
            <Col
                style={{backgroundColor: '#eeeeee', padding: "10px 20px", border: '1'}}
                xs={{span: 24}} sm={{span: 14}}
            >
                <h3>产品规格</h3>
                <Form
                    style={{width: '100%'}}
                    labelCol={{
                        xs: {span: 24},
                        sm: {span: 6}
                    }}
                    wrapperCol={{
                        xs: {span: 24},
                        sm: {span: 18},
                    }}
                    layout="horizontal"
                    name={"spec"}
                    onValuesChange={onSpecValueChange}
                >
                    {
                        productSpecs?.map(spec =>
                            <Form.Item key={spec.id} name={spec.name} label={spec.name}
                                       initialValue={(productModel?.specs || {})[spec.name]}
                            >
                                {spec.entryMethod === 'custom' ? <Input style={{flex: '1 0 0px'}}/> :
                                    <Select
                                        showArrow
                                        allowClear={true}
                                        {...spec.selectType === 'multiple' ? {mode: 'multiple'} : {}}
                                        style={{flex: '1 0 0px'}} options={
                                        spec.valueArray?.map(val =>
                                            ({label: val, value: val}))}
                                    />}
                            </Form.Item>
                        )
                    }
                </Form>
            </Col>
        </Row>
    );
}

export default SpecAddForm;