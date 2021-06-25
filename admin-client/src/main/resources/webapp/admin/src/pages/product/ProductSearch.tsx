import React, {useEffect} from 'react';
import {Button, Col, Form, Input, Row, Select, TreeSelect} from "antd";
import styled from "styled-components";
import {ProductQueryModel, RouterState} from "../../model";
import {useAppDispatch, useAppSelector} from "../../store/hooks";
import {getProductList, selectProductList} from "../../store/slices/productSlice";
import {selectCategoryReducer, selectUITree} from "../../store/slices/cateogrySlice";
import {useHistory} from "react-router-dom";
import {getBrandList, selectBrandReducer} from "../../store/slices/brandSlice";
import {AutoComplete} from 'antd';
import {CategoryTree} from "../category/CategoryConvertor";
import {useForm} from "antd/es/form/Form";
const { Option } = AutoComplete;

const StyledCol = styled(Col)`
  padding: 10px;
`

function ProductSearch() {
    const [form] = useForm();
    let uiTree = useAppSelector(selectUITree) as CategoryTree[];
    uiTree = [...uiTree].slice(1);
    let productPageModel = useAppSelector(selectProductList);
    let categoryReducer = useAppSelector(selectCategoryReducer);
    let dispatch = useAppDispatch();
    let history = useHistory<RouterState>();
    let brandState = useAppSelector(selectBrandReducer);
    const brandOptions = brandState.data?.items.map((item) => ({
        value: String(item.id),
        label:item.name
    }))
    useEffect(() => {
        dispatch(getBrandList());
    }, [dispatch]);

    function onSubmit(value: ProductQueryModel) {
        dispatch(getProductList({currentPage: 0, pageSize: productPageModel?.pageSize || 20, example: value}));
    }


    function onAddClick() {
        history.push({pathname:"/product/add",state:{updateMenu:true}});
    }

    function onResetClick() {
        form.resetFields();
    }

    return (
        <Form style={{width: '100%'}} layout={'inline'}
              onFinish={onSubmit}
              form={form}
        >
            <Row gutter={20} justify={'end'}>
                <StyledCol span={6}>
                    <Form.Item
                        name={`name`}
                        label={'商品名称'}
                    >
                        <Input allowClear={true} placeholder="商品名称"/>
                    </Form.Item>
                </StyledCol>
                <StyledCol span={6}>
                    <Form.Item
                        name={`itemNo`}
                        label={'商品货号'}
                    >
                        <Input  allowClear={true}/>
                    </Form.Item>
                </StyledCol>
                <StyledCol span={6}>
                    <Form.Item label="商品分类"
                               name="category">
                        <TreeSelect allowClear={true}
                                    loading={categoryReducer.categoryList.status === 'loading'}
                                    treeData={uiTree}
                        />
                    </Form.Item>
                </StyledCol>
                <StyledCol span={6}>
                    <Form.Item label="商品品牌"
                               name="brand"
                    >
                        <Select
                            allowClear={true}
                            options={brandOptions}
                            showSearch
                            optionFilterProp="children"
                        >
                        </Select>
                    </Form.Item>
                </StyledCol>
                <StyledCol span={6}>
                    <Form.Item
                        name={`status`}
                        label={'上架状态'}
                    >
                        <Select allowClear={true} notFoundContent={<div>数据加载错误,请检查网络</div>} >
                            <Option  value="ON_SALE">上架中</Option>
                            <Option value="OUT_OF_ORDER">已下架</Option>
                        </Select>
                    </Form.Item>
                </StyledCol>
                <StyledCol span={18} style={{display:'flex',justifyContent:'flex-end'}}>
                    <Form.Item
                    >
                    <Button style={{marginRight: '20px'}} htmlType={'submit'} type={'primary'}>搜索</Button>
                    <Button style={{marginRight: '20px'}} onClick={onResetClick}>重置</Button>
                    <Button onClick={onAddClick}>添加</Button>
                    </Form.Item>
                </StyledCol>
            </Row>
        </Form>
    );
}

export default ProductSearch;