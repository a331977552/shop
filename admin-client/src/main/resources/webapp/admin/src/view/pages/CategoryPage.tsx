import React, {Component} from 'react';
import {Button, Table, Space, Tag} from "antd";
import {connect, ConnectedProps} from 'react-redux'
import {RootState} from "../../store/store";
import {categorySlice, getCategoryList, selectCategoryReducer} from "../../store/slices/cateogrySlice";
import {CategoryModel} from "../../model";


const columns = [
    {
        title: 'ID',
        dataIndex: 'id',
        key: 'id',
    },
    {
        title: '名称',
        dataIndex: 'name',
        key: 'name',
    },
    {
        title: '层级',
        dataIndex: 'level',
        key: 'level',

    },
    {
        title: '下级',
        dataIndex: 'isleaf',
        key: 'isleaf',
        render:(text:string, record:CategoryModel, index:number)=> {
            console.log(text,record,index)
            return <Button disabled={record.isleaf} size={"small"}>查看</Button>
        }
    },
    {
        title: '优先级',
        dataIndex: 'priority',
        key: 'priority',
    },
    {
        title: '是否显示',
        dataIndex: 'visible',
        key: 'visible',
        render:(text:string, record:CategoryModel, index:number)=> {
            console.log(text,record,index)
            return <Tag> {record.visible?"显示":'隐藏'}</Tag>
        }
    },
    {
        title: '商品单位',
        dataIndex: 'suffix',
        key: 'suffix',
    },
    {
        title: '关键字',
        dataIndex: 'keyword',
        key: 'keyword',
    },
    {
        fixed:false,
        title: '操作',
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
        this.props.getCategoryList({currentPage:0,pageSize:20});
    }

    render() {
        console.log(this.props)
        const {errorMsg,data,status} = this.props;
        return (
            status === 'error' ? <div> error: {errorMsg}</div> :
                <div style={{display: 'flex', height: '100%', flexDirection: 'column'}}>
                    <div><Button style={{float: 'right'}}>添加种类</Button></div>
                    <div style={{width: '100%', flex: '1 0 0px', overflow: 'auto', marginTop: '10px'}}>
                        <Table loading={status === 'loading'} rowKey={"id"} dataSource={this.props.data?.items} columns={columns}/>;
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




