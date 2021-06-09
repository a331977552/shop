import React, {Component} from 'react';
import {Spin, Input, Button, Select, Form, Row, Col, Table, Space} from "antd";
import {connect, ConnectedProps} from 'react-redux'
import {RootState} from "../../store/store";
import {getProductList, productSlice, selectProductReducer} from "../../store/slices/productSlice";
import styled from "styled-components";

const {Option} = Select;
const StyledCol = styled(Col)`
  padding: 10px;
`


const columns = [
    {
        title: 'ID',
        dataIndex: 'id',
        key: 'id',
    },
    {
        title: 'name',
        dataIndex: 'name',
        key: 'name',
    },
    {
        title: 'category',
        dataIndex: 'category',
        key: 'category',
    },
    {
        title: 'status',
        dataIndex: 'status',
        key: 'status',
    },
    {
        title: 'thumbnailImg',
        dataIndex: 'thumbnailImg',
        key: 'thumbnailImg',
    },
    {
        title: 'brand',
        dataIndex: 'brand',
        key: 'brand',
    },
    {
        title: 'price',
        dataIndex: 'price',
        key: 'price',
    },
    {
        title: 'sku',
        dataIndex: 'sku',
        key: 'sku',
    },
    {
        title: 'Action',
        dataIndex: '',
        key: 'x',
        render: () => <Space size="middle">
            <Button size={'small'} type={'link'}>删除</Button>
            <Button size={'small'} type={'link'}>更新</Button>
        </Space>
        ,
    },
];

class ProductListPage extends Component<PropsFromRedux> {


    componentDidMount() {
        this.props.getProductList(null);
    }

    render() {
        const {status, errorMsg} = this.props;
        return (
            status === 'error' ? <div> error: {errorMsg}</div> :
                <div style={{display: 'flex', height: '100%', flexDirection: 'column'}}>
                    <Spin style={{width: '100%', height: '100%'}} spinning={status === 'loading'}>
                        <Form style={{width: '100%'}} layout={'inline'}>
                            <Row gutter={24}>
                                <StyledCol span={8}>
                                    <Form.Item
                                        name={`product.name`}
                                        label={'商品名称'}
                                    >
                                        <Input placeholder="商品名称"/>
                                    </Form.Item>
                                </StyledCol>
                                <StyledCol span={8}>
                                    <Form.Item
                                        name={`product.ibnb`}
                                        label={'商品货号'}
                                    >
                                        <Input placeholder="商品货号"/>
                                    </Form.Item>
                                </StyledCol>
                                <StyledCol span={8}>
                                    <Form.Item
                                        name={`product.category`}
                                        label={'商品分类'}
                                    >
                                        <Input placeholder="商品分类"/>
                                    </Form.Item>
                                </StyledCol>
                                <StyledCol span={8}>
                                    <Form.Item
                                        name={`product.onsale`}
                                        label={'上架状态'}
                                        initialValue={"ON_SALE"}
                                    >
                                        <Select placeholder={"上架状态"}>
                                            <Option value="ON_SALE">上架中</Option>
                                            <Option value="OUT_OF_ORDER">已下架</Option>
                                        </Select>
                                    </Form.Item>
                                </StyledCol>
                                <StyledCol span={8}>
                                    <Button style={{marginRight: '20px'}} type={'primary'}>搜索</Button>
                                    <Button>重置</Button>
                                </StyledCol>
                            </Row>
                        </Form>
                    </Spin>
                    <div style={{width: '100%', flex: '1 0 0px', overflow: 'auto', marginTop: '10px'}}>
                        <Table loading={status === 'loading'} dataSource={this.props.data?.products} columns={columns}/>;
                    </div>
                </div>
        );
    }
}


const mapState = (state: RootState) => {
    return selectProductReducer(state);
}

const connector = connect(mapState,
    {...productSlice.actions, getProductList})

type PropsFromRedux = ConnectedProps<typeof connector>
export default connector(ProductListPage);




