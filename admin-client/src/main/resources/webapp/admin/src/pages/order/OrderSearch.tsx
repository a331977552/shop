import React, {useEffect} from 'react';
import {Button, Col, Form, Input, Row, Select} from "antd";
import styled from "styled-components";
import {ProductQueryModel, RouterState} from "../../model";
import {useAppDispatch, useAppSelector} from "../../store/hooks";
import {useHistory} from "react-router-dom";
import {AutoComplete} from 'antd';
import {useForm} from "antd/es/form/Form";
import {getOrderList, selectOrderList} from "../../store/slices/orderSlice";

const {Option} = AutoComplete;

const StyledCol = styled(Col)`
  padding: 10px;
`

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

function OrderSearch() {
    const [form] = useForm();
    let orderPageModel = useAppSelector(selectOrderList);
    let dispatch = useAppDispatch();

    function onSubmit(value: ProductQueryModel) {
        console.log(value);
        dispatch(getOrderList({currentPage: 0, pageSize: orderPageModel?.pageSize || 20, example: value}));
    }


    function onResetClick() {
        form.resetFields();
    }

    return (
        <Form style={{width: '100%'}} layout={'inline'}
              onFinish={onSubmit}
              form={form}
        >
            <Row gutter={20}>
                <StyledCol md={{span: 6}} sm={{span: 12}} xs={{span:24}}>
                    <Form.Item
                        name={`orderNum`}
                        label={'订单号'}
                    >
                        <Input allowClear={true} placeholder="订单号"/>
                    </Form.Item>
                </StyledCol>
                <StyledCol md={{span: 6}} sm={{span: 12}}  xs={{span:24}}>
                    <Form.Item
                        name={`username`}
                        label={'用户账户'}
                    >
                        <Input allowClear={true}/>
                    </Form.Item>
                </StyledCol>

                <StyledCol md={{span: 6}} sm={{span: 12}} xs={{span: 24}}>
                    <Form.Item label="状态"
                               name="status"
                    >
                        <Select
                            allowClear={true}
                            options={statusOptions}
                        >
                        </Select>
                    </Form.Item>
                </StyledCol>
                <StyledCol md={{span: 6}} sm={{span: 12}} xs={{span: 24}}>
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
                </StyledCol>
                <StyledCol md={{span: 6}} sm={{span: 12}} xs={{span: 24}}>
                    <Form.Item
                        name={`receiverName`}
                        label={'收货人'}
                    >
                        <Input allowClear={true}/>
                    </Form.Item>
                </StyledCol>
                <StyledCol md={{span: 18}} sm={{span: 12}}  xs={{span: 24}} style={{display: 'flex', justifyContent: 'flex-end'}}>
                    <Form.Item
                    >
                        <Button style={{marginRight: '20px'}} htmlType={'submit'} type={'primary'}>搜索</Button>
                        <Button style={{marginRight: '20px'}} onClick={onResetClick}>重置</Button>
                    </Form.Item>
                </StyledCol>
            </Row>
        </Form>
    );
}

export default OrderSearch;