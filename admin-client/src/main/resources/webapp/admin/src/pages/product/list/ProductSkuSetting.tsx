import React, {useState} from 'react';
import {ProductModel} from "../../../model";
import {Button, Space, Modal} from "antd";
import AttrContent from "./AttrContent";
import SpecsContent from "./SpecsContent";

function ProductSkuSetting(record:ProductModel) {

    const [modelAttrVisible,setModelAttrVisible] = useState<boolean>(false);
    const [modelSpecVisible,setModelSpecVisible] = useState<boolean>(false);
    function onSpecClick() {
        setModelSpecVisible(true);
        setModelAttrVisible(false);
    }
    function onAttrClick() {
        setModelSpecVisible(false);
        setModelAttrVisible(true);
    }

    function onAttrCancel() {
        setModelAttrVisible(false);
    }

    function onSpecCancel() {
        setModelSpecVisible(false)
    }

    return (
        <Space style={{display:'flex'}}>
            <Button disabled={!record.skuList || record.skuList.length === 0} size={'small'} shape="round"  type="primary"  onClick={onAttrClick}>
                属性
            </Button>
            <Button disabled={!record.specs || record.specs.length === 0} size={'small'} shape="round"  type="primary" onClick={onSpecClick} >
                规格
            </Button>
            <Modal  width={"90%"} destroyOnClose={true} title={<span>商品<span style={{fontWeight:'bold'}}> {record.name} </span>属性</span>} centered={true}  onCancel={onAttrCancel}  footer={null} closable={true} visible={modelAttrVisible} >
               <AttrContent {...record} />
            </Modal>
            <Modal  width={"90%"} destroyOnClose={true} title={<span>商品<span style={{fontWeight:'bold'}}> {record.name} </span>规格</span>}  centered={true}  onCancel={onSpecCancel}  footer={null} closable={true} visible={modelSpecVisible} >
                <SpecsContent {...record} />
            </Modal>
        </Space>
    );
}

export default ProductSkuSetting;