import React, {} from 'react';
import {Button, Col, Form, Input, Row, Select, TreeSelect} from "antd";
import styled from "styled-components";
import { ProductQueryModel} from "../../model";
import { useAppSelector} from "../../store/hooks";
import {selectCategoryDataReducer, selectCategoryReducer, selectUITree} from "../../store/slices/cateogrySlice";

const {Option} = Select;
const StyledCol = styled(Col)`
  padding: 10px;
`

function ProductSearch() {

    const uiTree = useAppSelector(selectUITree);

    function onSubmit(value: ProductQueryModel) {
        console.log(value)
    }

    function onRetry() {

    }

    return (
            <Form style={{width: '100%'}} layout={'inline'}
                  onFinish={onSubmit}
            >
                <Row gutter={24}>
                    <StyledCol span={8}>
                        <Form.Item
                            name={`name`}
                            label={'商品名称'}
                        >
                            <Input placeholder="商品名称"/>
                        </Form.Item>
                    </StyledCol>
                    <StyledCol span={8}>
                        <Form.Item
                            name={`itemNo`}
                            label={'商品货号'}
                        >
                            <Input placeholder="商品货号"/>
                        </Form.Item>
                    </StyledCol>
                    <StyledCol span={8}>
                        <Form.Item label="商品分类" initialValue={'0'}
                                   name="category">
                            <TreeSelect notFoundContent={<div>数据加载错误,请检查网络</div>}
                                        treeData={uiTree}
                            />
                        </Form.Item>
                    </StyledCol>
                    <StyledCol span={8}>
                        <Form.Item
                            name={`status`}
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
                        <Button style={{marginRight: '20px'}} htmlType={'submit'} type={'primary'}>搜索</Button>
                        <Button>重置</Button>
                    </StyledCol>
                </Row>
            </Form>
    );
}

export default ProductSearch;