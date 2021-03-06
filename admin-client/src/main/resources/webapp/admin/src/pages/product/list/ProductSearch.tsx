import React, {useEffect} from 'react';
import {Button, Col, Form, Input, Row, Select, TreeSelect} from "antd";
import styled from "styled-components";
import {ProductQueryModel, RouterState} from "../../../model";
import {useAppDispatch, useAppSelector} from "../../../store/hooks";
import {selectCategoryReducer, selectUITree} from "../../../store/slices/cateogrySlice";
import {useHistory} from "react-router-dom";
import {getBrandList, selectBrandReducer} from "../../../store/slices/brandSlice";
import {AutoComplete} from 'antd';
import {CategoryTree} from "../../category/CategoryConvertor";
import {useForm} from "antd/es/form/Form";

const {Option} = AutoComplete;

const StyledCol = styled(Col)`
  padding: 10px;
`

function StyledColHOC(props: any) {
    return <StyledCol xxl={{span:6}} xl={{span:8}} md={{span: 12}} sm={{span: 24}} xs={{span: 24}}>{props.children}</StyledCol>
}

function ProductSearch({setProductQueryModel}: { setProductQueryModel: React.Dispatch<React.SetStateAction<ProductQueryModel | undefined>> }) {
    const [form] = useForm();
    let uiTree = useAppSelector(selectUITree) as CategoryTree[];
    uiTree = [...uiTree].slice(1);
    let categoryReducer = useAppSelector(selectCategoryReducer);
    let dispatch = useAppDispatch();
    let history = useHistory<RouterState>();
    let brandState = useAppSelector(selectBrandReducer);
    const brandOptions = brandState.data?.items.map((item) => ({
        value: String(item.id),
        label: item.name
    }))
    useEffect(() => {
        dispatch(getBrandList());
    }, [dispatch]);

    function onSubmit(value: ProductQueryModel) {
        setProductQueryModel(value);
    }


    function onAddClick() {
        history.push({pathname: "/product/add", state: {updateMenu: true}});
    }

    function onResetClick() {
        form.resetFields();
        setProductQueryModel(undefined);
    }

    return (
        <Form style={{width: '100%'}} layout={'inline'}
              onFinish={onSubmit}
              form={form}
        >
            <Row gutter={20} style={{width:'100%'}}>
                <StyledColHOC>
                    <Form.Item
                        name={`name`}
                        label={'????????????'}
                    >
                        <Input allowClear={true} placeholder="????????????"/>
                    </Form.Item>
                </StyledColHOC>
                <StyledColHOC>
                    <Form.Item
                        name={`itemNo`}
                        label={'????????????'}
                    >
                        <Input allowClear={true}/>
                    </Form.Item>
                </StyledColHOC>
                <StyledColHOC>
                    <Form.Item label="????????????"
                               name="categoryId">
                        <TreeSelect allowClear={true}
                                    loading={categoryReducer.categoryList.status === 'loading'}
                                    treeData={uiTree}
                        />
                    </Form.Item>
                </StyledColHOC>
                <StyledColHOC>
                    <Form.Item label="????????????"
                               name="brandId"
                    >
                        <Select
                            allowClear={true}
                            options={brandOptions}
                            showSearch
                            optionFilterProp="children"
                        >
                        </Select>
                    </Form.Item>
                </StyledColHOC>
                <StyledColHOC>
                    <Form.Item
                        name={`status`}
                        label={'????????????'}
                    >
                        <Select allowClear={true} notFoundContent={<div>??????????????????,???????????????</div>}>
                            <Option value="ON_SALE">?????????</Option>
                            <Option value="OUT_OF_ORDER">?????????</Option>
                        </Select>
                    </Form.Item>
                </StyledColHOC>
                <StyledCol xl={{span:8}} xxl={{span:18}} md={{span: 12}} sm={{span: 24}} xs={{span: 24}}
                           style={{display: 'flex', justifyContent: 'flex-end'}}>
                    <Form.Item
                    >
                        <Button style={{marginRight: '20px'}} htmlType={'submit'} type={'primary'}>??????</Button>
                        <Button style={{marginRight: '20px'}} onClick={onResetClick}>??????</Button>
                        <Button onClick={onAddClick}>??????</Button>
                    </Form.Item>
                </StyledCol>
            </Row>
        </Form>
    );
}

export default ProductSearch;