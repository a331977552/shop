import React, {Component} from 'react';
import {Button, Table, Tag, Switch, Space} from "antd";
import {connect, ConnectedProps} from 'react-redux'
import {RootState} from "../../store/store";
import {categorySlice, getCategoryList, selectCategoryReducer} from "../../store/slices/cateogrySlice";
import {CategoryModel} from "../../model";
import CategoryDeletion from "./CategoryDeletion";
import CategorySetting from "./CategorySetting";
import { withRouter } from "react-router";
import {RouteComponentProps} from "react-router";


const levelColor = [
    "#f50",
    "#2db7f5",
    "#87d068",
    "#108ee9",
    "gold",
    "lime",
    "green",
    "cyan",
    "blue"
]
const columns = [
    {
        title: '序号(编号)',
        dataIndex: 'id',
        key: 'id',
        render:(text:string,record:CategoryModel,index:number)=>{
            return <Space><span>{index}</span> <Tag color={"lime"}>{record.id}</Tag></Space>
        }
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
        render: (text: string, record: CategoryModel) => {
            return <Tag color={levelColor[record.level]}>{record.level + 1}</Tag>
        }
    },

    {
        title: '优先级',
        dataIndex: 'priority',
        key: 'priority',
    },
    {
        title: '设置',
        dataIndex: '',
        key: 'y',
        render: (text: string, record: CategoryModel, index: number) => {
            return <CategorySetting record={record}/>
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
        fixed: false,
        title: '操作',
        dataIndex: '',
        key: 'x',
        render: (tex:string,record: CategoryModel) => <CategoryDeletion record={record}/>
        ,
    },
];


class CategoryPage extends Component<PropsFromRedux> {



    componentDidMount() {
        let currentPage = (this.props.categoryList.data?.currentPage) || 0;
        let pageSize = (this.props.categoryList.data?.pageSize) || 20;
        this.props.getCategoryList({currentPage: currentPage, pageSize: pageSize});
    }


    onAddClick = () => {
        const { history } = this.props;
        history.push("/product/category/add")
    };

    render() {
        const {categoryList} = this.props;
        const {errorMsg,status,data} = categoryList;
        return (
            status === 'error' ? <div> error: {errorMsg}</div> :
                <div style={{display: 'flex', height: '100%', flexDirection: 'column'}}>
                    <div><Button style={{float: 'right'}} onClick={this.onAddClick}>添加种类</Button></div>
                    <div style={{width: '100%', flex: '1 0 0px', overflow: 'auto', marginTop: '10px'}}>
                        <Table loading={status === 'loading'} rowKey={"id"} dataSource={data?.items}
                               columns={columns}/>;
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

type PropsFromRedux = ConnectedProps<typeof connector> & RouteComponentProps
export default withRouter(connector(CategoryPage));




