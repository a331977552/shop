import React, {useCallback, useEffect, useState} from 'react';
import {getShippingAddressByOrderIdAPI} from "../../../api/OrderAPI";
import {GenericState} from "../../../store/hooks";
import {ShippingAddressModel} from "../../../model";
import StatusView from "../../../components/StatusView";
import {Form, Input, Button} from "antd";

const layout = {
    labelCol: {span: 6},
    wrapperCol: {span: 18},
};

function OrderShippingPage(props:{oid:string}) {
    const [shippingAddressModel, setShippingAddressModel] = useState<GenericState<ShippingAddressModel>>({status: 'loading'});
    const shippingAddress = shippingAddressModel.data;
    const orderId = props.oid;

    const retrieveAddressInfo = useCallback(() => {
        setShippingAddressModel({status:'loading'})
        getShippingAddressByOrderIdAPI(orderId).then(r => {
            setShippingAddressModel({status: 'finished', data: r.result})
        }).catch((error) => {
            setShippingAddressModel({status: 'error', errorMsg: error.errorMsg})
        });
    }, [setShippingAddressModel, orderId]);

    useEffect(() => {
        retrieveAddressInfo()
    }, [retrieveAddressInfo]);


    function onFinish(value: ShippingAddressModel) {
        console.log(value);
    }
    return (
        <StatusView status={shippingAddressModel.status} retry={retrieveAddressInfo} errorMsg={shippingAddressModel.errorMsg}>
            <Form {...layout} onFinish={onFinish} labelAlign={'right'}>
                <Form.Item name={'id'} hidden={true} initialValue={shippingAddress?.id}>
                    <Input/>
                </Form.Item>
                <Form.Item name={'sOrderId'} hidden={true} initialValue={shippingAddress?.sorderId}>
                    <Input/>
                </Form.Item>
                <Form.Item name={"customerName"} label="收货人姓名"  rules={[{required: true,message:'收货人姓名不能为空'}]}
                           initialValue={shippingAddress?.customerName}>
                    <Input readOnly={true}/>
                </Form.Item>
                <Form.Item name={"homeAddress"} label="收货人地址" rules={[{required: true,message:'收货人地址不能为空'}]}
                           initialValue={shippingAddress?.homeAddress} >
                    <Input  readOnly={true}/>
                </Form.Item>
                <Form.Item name={"phoneNumber"} label="收货人电话" rules={[{required: true,message:'收货人电话不能为空'}]}
                           initialValue={shippingAddress?.phoneNumber}>
                    <Input  readOnly={true}/>
                </Form.Item>
                <Form.Item name={"postCode"} label="邮编" initialValue={shippingAddress?.postCode}>
                    <Input  readOnly={true}/>
                </Form.Item>


                <Form.Item name={"trackingNum"} label="快递单号" rules={[{required: true,message:'快递单号不能为空'}]}>
                    <Input />
                </Form.Item>


                <Form.Item name={"deliveryNameId"} label="快递公司" rules={[{required: true,message:'快递单号不能为空'}]}>
                    <Input  />
                </Form.Item>



                <Form.Item wrapperCol={{
                    offset:6
                }}>
                    <Button type="primary" htmlType="submit">
                        确认发货
                    </Button>
                </Form.Item>
            </Form>
        </StatusView>
    );
}

export default OrderShippingPage;