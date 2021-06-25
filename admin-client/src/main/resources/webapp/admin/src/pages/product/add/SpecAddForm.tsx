import React, {useEffect, useState} from 'react';
import {KeyVals, ProductSpecModel} from "../../../model";
import {Form, Input, message, Select} from "antd";
import {getProductSpecListAPI} from "../../../api/ProductSpecAPI";
import {loadItem, removeItem, saveItem} from "../../../services";

function saveSpecs(attrs: KeyVals) {
    saveItem("product_adding_specs", JSON.stringify(attrs));
}

function loadSpecs() {
    return loadItem("product_adding_specs");
}

function removeSpecsFromCache() {
    return removeItem("product_adding_specs");
}


function SpecAddForm(
    props: {
        onCategoryChangeFuncSpecRef:(onCategoryChange:()=>void)=>void,
        category?: number
    }
) {
    const {category,onCategoryChangeFuncSpecRef} = props;
    const [specs, setSpecs] = useState<KeyVals>(() => {
        const specs = loadSpecs();
        return specs ? JSON.parse(specs) : {};
    });
    const [productSpecs, setProductSpecs] = useState<Array<ProductSpecModel>>();

    onCategoryChangeFuncSpecRef(()=>{
        setSpecs({});
        removeSpecsFromCache();
    });
    useEffect(() => {
        if (category) {
            getProductSpecListAPI({example: {categoryId: category}}).then((result) => {
                const specsForm = result.result?.items?.map(item => ({
                    ...item, valueArray: Array.from(new Set(item.value?.split("\n")))
                }));
                setProductSpecs(specsForm);
            }).catch((error) => {
                message.error(error.msgDetail, 3);
            })
        }
    }, [setProductSpecs, category,setSpecs]);


    function onSpecValueChange(changedFields: any, allFields: { [key: string]: string[] }) {
        saveSpecs(allFields);
        setSpecs(allFields);
    }

    if ((productSpecs?.length || 0) === 0)
        return null;

    return (<div
            style={{
                borderWidth: '1px',
                borderStyle: 'solid',
                borderColor: '#F1F1F1',
                borderRadius: '2px',
                padding: "10px 20px",
                width: '100%',
                marginBottom: '20px'
            }}>
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
                preserve={false}
                onValuesChange={onSpecValueChange}
            >
                {
                    productSpecs?.map(spec =>
                        <Form.Item key={spec.id} name={spec.name} label={spec.name}
                                   initialValue={(specs || {})[spec.name]}
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
        </div>
    );
}

export default SpecAddForm;