import React, {useCallback, useEffect, useState} from 'react';
import {useHistory} from "react-router-dom";
import {parseSearchParams} from "../../../services";
import {getShippingAddressByOrderIdAPI} from "../../../api/OrderAPI";
import {GenericState} from "../../../store/hooks";
import {ShippingAddressModel} from "../../../model";
import StatusView from "../../../components/StatusView";
import {Form, Input, InputNumber, Button} from "antd";
const layout = {
    labelCol: { span: 8 },
    wrapperCol: { span: 16 },
};
function OrderShippingPage() {
    const [shippingAddressModel,setShippingAddressModel ] = useState<GenericState<ShippingAddressModel>>({status:'loading'});
    let history = useHistory();
    let search = history.location.search;
    let params = parseSearchParams(search);

    const orderId = params['oid'] as string;
    const getAddress = useCallback(()=>{
        getShippingAddressByOrderIdAPI(orderId).then(r => {
            setShippingAddressModel({status:'finished',data:r.result})
        }).catch((error)=>{
            setShippingAddressModel({status:'error',errorMsg:error.errorMsg})
        });
    },[setShippingAddressModel,orderId]);

    useEffect(()=>{
        getAddress()
    },[getAddress]);


    function onFinish(value:ShippingAddressModel) {

    }

    return (
        <StatusView status={shippingAddressModel.status} retry={getAddress} errorMsg={shippingAddressModel.errorMsg}>
            <Form {...layout} name="nest-messages" onFinish={onFinish} >
                <Form.Item name={['user', 'name']} label="Name" rules={[{ required: true }]}>
                    <Input />
                </Form.Item>
                <Form.Item name={['user', 'email']} label="Email" rules={[{ type: 'email' }]}>
                    <Input />
                </Form.Item>
                <Form.Item name={['user', 'age']} label="Age" rules={[{ type: 'number', min: 0, max: 99 }]}>
                    <InputNumber />
                </Form.Item>
                <Form.Item name={['user', 'website']} label="Website">
                    <Input />
                </Form.Item>
                <Form.Item name={['user', 'introduction']} label="Introduction">
                    <Input.TextArea />
                </Form.Item>
                <Form.Item wrapperCol={{ ...layout.wrapperCol, offset: 8 }}>
                    <Button type="primary" htmlType="submit">
                        Submit
                    </Button>
                </Form.Item>
            </Form>
        </StatusView>
    );
}

export default OrderShippingPage;