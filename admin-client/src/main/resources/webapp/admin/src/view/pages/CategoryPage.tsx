import React, {Component} from 'react';
import {Button, Table, Space, Tag, Popover, Popconfirm, Switch} from "antd";
import {connect, ConnectedProps} from 'react-redux'
import {RootState} from "../../store/store";
import {categorySlice, getCategoryList, selectCategoryReducer} from "../../store/slices/cateogrySlice";
import {CategoryModel} from "../../model";


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


class CategoryPage extends Component<PropsFromRedux> {

    columns = [
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
            render: (text: string, record: CategoryModel, index: number) => {
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
                return <span>
                    <Switch checkedChildren="显示" unCheckedChildren="隐藏" checked={record.visible}/>
                <Button style={{marginLeft: '10px'}} disabled={record.isleaf} onClick={()=>{
                    this.onShowNextLevelClicked(record);
                }}
                        size={"small"}>{record.isleaf ? "没有下级" : "查看下级"}</Button>
            </span>
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
            render: () => <Space size="middle">
                <Popconfirm title={"确认要删除吗?"}><a>删除</a></Popconfirm>
                <a>更新</a>
            </Space>
            ,
        },
    ];


    onShowNextLevelClicked = (record: CategoryModel)=> {
        this.props.getCategoryList({currentPage: 0, pageSize: 20,example:{parent:record.id}});
    }

    componentDidMount() {
        let currentPage = (this.props.data?.currentPage) || 0;
        let pageSize = (this.props.data?.pageSize) || 20;
        this.props.getCategoryList({currentPage: currentPage, pageSize: pageSize});
    }

    render() {
        console.log(this.props)
        const {errorMsg, data, status} = this.props;
        return (
            status === 'error' ? <div> error: {errorMsg}</div> :
                <div style={{display: 'flex', height: '100%', flexDirection: 'column'}}>
                    <div><Button style={{float: 'right'}}>添加种类</Button></div>
                    <div style={{width: '100%', flex: '1 0 0px', overflow: 'auto', marginTop: '10px'}}>
                        <Table loading={status === 'loading'} rowKey={"id"} dataSource={this.props.data?.items}
                               columns={this.columns}/>;
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




