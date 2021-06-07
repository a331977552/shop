import React, {Component} from 'react';
import {Spin, Input, Button, Select, Form, Row, Col, Table, Space} from "antd";
import {connect, ConnectedProps} from 'react-redux'
import {RootState} from "../../store/store";
import styled from "styled-components";
import {categorySlice, getCategoryList, selectCategoryReducer} from "../../store/slices/cateogrySlice";

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
        title: 'parent',
        dataIndex: 'parent',
        key: 'parent',
    },
    {
        title: 'Action',
        dataIndex: '',
        key: 'x',
        render: () => <Space size="middle">
            <a>Delete</a>
            <a>update</a>
        </Space>
        ,
    },
];

class CategoryPage extends Component<PropsFromRedux> {


    componentDidMount() {
        this.props.getCategoryList({});
    }

    render() {
        console.log(this.props)
        const {status} = this.props;
        const {errorMsg} = this.props;
        return (
            status === 'error' ? <div> error: {errorMsg}</div> :
                <div style={{display: 'flex', height: '100%', flexDirection: 'column'}}>
                    <div><Button style={{float: 'right'}}>ADD</Button></div>
                    <div style={{width: '100%', flex: '1 0 0px', overflow: 'auto', marginTop: '10px'}}>
                        <Table loading={status === 'loading'} dataSource={this.props.data} columns={columns}/>;
                    </div>
                </div>
        );
    }
}


const mapState = (state: RootState) => {
    return selectCategoryReducer(state);
}

const connector = connect(mapState,
    {...categorySlice.actions, getCategoryList})

type PropsFromRedux = ConnectedProps<typeof connector>
export default connector(CategoryPage);




