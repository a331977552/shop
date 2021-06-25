import React, {Component} from 'react';
import {Button, Table, Tag, Space} from "antd";
import {connect, ConnectedProps} from 'react-redux'
import {RootState} from "../../store/store";
import {categorySlice, getCategoryList, selectCategoryReducer} from "../../store/slices/cateogrySlice";
import {CategoryModel} from "../../model";
import CategoryOperation from "./CategoryOperation";
import CategorySetting from "./CategorySetting";
import {withRouter} from "react-router";
import {RouteComponentProps} from "react-router";
import CategoryArgs from "./CategoryArgs";
import {findCategoriesByParentID} from "./CategoryConvertor";
import {paramParser} from "../../services";
import StatusView from "../../components/StatusView";


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
        render: (text: string, record: CategoryModel, index: number) => {
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
        title: '排序',
        dataIndex: 'priority',
        key: 'priority',
    },
    {
        title: '单位',
        dataIndex: 'suffix',
        key: 'suffix',
    },
    {
        title: '关键字',
        dataIndex: 'keyword',
        key: 'keyword',
    }, {
        title: '设置',
        dataIndex: '',
        key: 'y',
        render: (text: string, record: CategoryModel, index: number) => {
            return <CategorySetting record={record}/>
        }
    },
    {
        title: '属性设置',
        dataIndex: '',
        key: 'y',
        render: (text: string, record: CategoryModel, index: number) => {
            return <CategoryArgs {...record}/>
        }
    },
    {
        fixed: false,
        title: '操作',
        dataIndex: '',
        key: 'x',
        render: (tex: string, record: CategoryModel) => <CategoryOperation record={record}/>
        ,
    },
];


class CategoryListPage extends Component<PropsFromRedux> {


    state = {
        items: []
    }

    static getDerivedStateFromProps(nextProps: PropsFromRedux) {
        let items = nextProps.categoryList.data?.items as CategoryModel[];
        let param = paramParser(nextProps.location.search);
        const pid = param["pid"];
        return {items: pid ? findCategoriesByParentID(items, +pid) : items}
    }


    onAddClick = () => {
        const {history} = this.props;
        history.push("/category/add")
    };
    onRetry = () => {
        console.log("onretry")
    };

    render() {
        const {categoryList} = this.props;
        const {items} = this.state;
        const {errorMsg, status} = categoryList;


        return (
            <StatusView retry={this.onRetry} status={status} errorMsg={errorMsg}>
                <div style={{display: 'flex', height: '100%', flexDirection: 'column'}}>
                    <div><Button style={{float: 'right'}} onClick={this.onAddClick}>添加种类</Button></div>
                    <div style={{width: '100%', flex: '1 0 0px', overflow: 'auto', marginTop: '10px'}}>
                        <Table childrenColumnName={"null"} loading={status === 'loading'} rowKey={"id"}
                               dataSource={items}
                               columns={columns}
                               pagination={{defaultPageSize: 20, total: categoryList.data?.totalElements}}
                        />
                    </div>
                </div>
            </StatusView>

        );
    }

}


const mapState = (state: RootState) => {
    return selectCategoryReducer(state);
}

const connector = connect(mapState,
    {...categorySlice.actions, getCategoryList})

type PropsFromRedux = ConnectedProps<typeof connector> & RouteComponentProps<{ pid: string | undefined }>
export default withRouter(connector(CategoryListPage));




