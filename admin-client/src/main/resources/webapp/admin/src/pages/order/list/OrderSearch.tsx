import React from 'react';
import {Button, Col, Form, Input, Row, Select} from "antd";
import styled from "styled-components";
import {OrderQueryModel, ProductQueryModel} from "../../../model";
import {useForm} from "antd/es/form/Form";


const StyledCol = styled(Col)`
  padding: 10px;
`
function StyledColHOC(props: any) {

    return <StyledCol xxl={{span:6}} xl={{span:8}} md={{span: 12}} sm={{span: 24}} xs={{span: 24}}>{props.children}</StyledCol>
}
const statusOptions = [{
    value: 'UNPAID',
    label: '未支付',
},
    {
        value: 'PAID',
        label: '已支付',
    },
    {
        value: 'SHIPPING',
        label: '快递中',
    },
    {
        value: 'SHIPPED',
        label: '已投递',
    },
    {
        value: 'FINISHED',
        label: '已完成',
    },
    {
        value: 'REFUND',
        label: '已退款',
    },
    {
        value: 'CLOSED',
        label: '已关闭',
    }
];

const orderSourceOptions = [
    {
        value: 'web',
        label: '网页',
    },
    {
        value: 'android_app',
        label: '安卓',
    },
    {
        value: 'ios_app',
        label: '苹果',
    },
    {
        value: 'mini_program',
        label: '小程序',
    }
]

function OrderSearch({ setOrderQueryModel}:
                         {  setOrderQueryModel: React.Dispatch<React.SetStateAction<OrderQueryModel | undefined>> }) {
    const [form] = useForm();
    function onSubmit(value: ProductQueryModel) {
        console.log(value);
        setOrderQueryModel(value);
    }


    function onResetClick() {
        form.resetFields();
        setOrderQueryModel(undefined);
    }

    return (
        <Form style={{width: '100%'}} layout={'inline'}
              onFinish={onSubmit}
              form={form}
        >
            <Row gutter={20} style={{width:'100%'}}>
                <StyledColHOC>
                    <Form.Item
                        name={`orderNum`}
                        label={'订单号'}
                    >
                        <Input allowClear={true} placeholder="订单号"/>
                    </Form.Item>
               </StyledColHOC>
                <StyledColHOC>
                    <Form.Item
                        name={`username`}
                        label={'用户账户'}
                    >
                        <Input allowClear={true}/>
                    </Form.Item>
               </StyledColHOC>

                <StyledColHOC>
                    <Form.Item label="状态"
                               name="status"
                    >
                        <Select
                            allowClear={true}
                            options={statusOptions}
                        >
                        </Select>
                    </Form.Item>
               </StyledColHOC>
                <StyledColHOC>
                    <Form.Item
                        name={`orderSource`}
                        label={'来源'}
                    >
                        <Select
                            allowClear={true}
                            options={orderSourceOptions}
                        >
                        </Select>
                    </Form.Item>
               </StyledColHOC>
                <StyledColHOC>
                    <Form.Item
                        name={`receiverName`}
                        label={'收货人'}
                    >
                        <Input allowClear={true}/>
                    </Form.Item>
               </StyledColHOC>
                <StyledCol xl={{span:8}} xxl={{span:18}} md={{span: 12}} sm={{span: 24}} xs={{span: 24}}
                           style={{display: 'flex', justifyContent: 'flex-end'}}>
                    <Form.Item
                    >
                        <Button style={{marginRight: '20px'}} htmlType={'submit'} type={'primary'}>搜索</Button>
                        <Button  onClick={onResetClick}>重置</Button>
                    </Form.Item>
                </StyledCol>
            </Row>
        </Form>
    );
}

export default OrderSearch;