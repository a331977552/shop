import React, {useState} from 'react';
import {Layout, Menu} from "antd";
import {LaptopOutlined, NotificationOutlined, UserOutlined, HomeOutlined} from "@ant-design/icons";
import {useHistory} from "react-router-dom";
import {KeyStr} from "../../model";

const {Sider} = Layout;
const {SubMenu} = Menu;

const proArray: KeyStr = {
    "product": '/pro',
    "category": '/pro',
    "brand": '/pro',
    "spec": '/pro',
    "attr": '/pro'
};

function getMenuPath(pathArr: string[]) {

    let selectedMenuKey = '/';
    if (pathArr.length >= 2) {
        selectedMenuKey =  "/" + pathArr.slice(1).join("/");
    }
    return selectedMenuKey;
}

function getOpenPath(pathArr: string[]):string {
    let openKey = "/";
    if (pathArr.length >= 2) {
        openKey = proArray[pathArr[1]] || '/'
    }
    return openKey;
}

function DrawerLeft() {
    const [collapsed, setCollapsed] = useState(false);
    const history = useHistory();
    const pathArr = history.location.pathname.split("/");

    const [selectedMenuKey,setSelectedMenuKey] = useState<string>(()=>getMenuPath(pathArr));
    const [openPath] = useState<string>(()=>getOpenPath(pathArr));
    const onCollapse = (collapsed: boolean) => {
        setCollapsed(collapsed);
    }

    history.listen((location)=>{
       if((location.state as any)?.updateMenu){
           setSelectedMenuKey(getMenuPath(location.pathname.split("/")));
       }
    });
    const onMenuSelected = ({key}: { item: any, key: string, keyPath: Array<string>, domEvent: any }) => {
        setSelectedMenuKey(key);
        history.push({pathname:key,state:{updateMenu:false}});
    };

    return (
        <Sider theme={'light'}
               style={{
                   overflow: 'auto',
                   height: '100vh',
                   left: 0
               }}
               collapsible
               collapsed={collapsed}
               onCollapse={onCollapse}>
            <Menu
                mode="inline"
                selectedKeys={[selectedMenuKey]}
                defaultOpenKeys={[openPath]}
                onSelect={onMenuSelected}
            >
                <Menu.Item key="/" icon={<HomeOutlined/>}>主页</Menu.Item>
                <SubMenu key="/pro" icon={<UserOutlined/>} title="商品">
                    <Menu.Item key="/product">商品列表</Menu.Item>
                    <Menu.Item key="/product/add">添加商品</Menu.Item>
                    <Menu.Item key="/category">分类列表</Menu.Item>
                    <Menu.Item key="/brand">品牌列表</Menu.Item>
                </SubMenu>
                <SubMenu key="/order" icon={<LaptopOutlined/>} title="订单">
                    <Menu.Item key="/order_list">订单列表</Menu.Item>
                    <Menu.Item key="/setting">订单设置</Menu.Item>
                    <Menu.Item key="/refund">退款处理</Menu.Item>
                    <Menu.Item key="/refund_reason">退款原因设置</Menu.Item>
                </SubMenu>
                <SubMenu key="/operation" icon={<NotificationOutlined/>} title="运营">
                    <Menu.Item key="/flashSaleList">限时秒杀</Menu.Item>
                    <Menu.Item key="/coupon">优惠券</Menu.Item>
                    <Menu.Item key="/productRecom">商品推荐</Menu.Item>
                    <Menu.Item key="/brandRecom">品牌推广</Menu.Item>
                </SubMenu>
                <SubMenu key="/auth" icon={<NotificationOutlined/>} title="权限">
                    <Menu.Item key="/user">用户管理</Menu.Item>
                    <Menu.Item key="/role">角色管理</Menu.Item>
                    <Menu.Item key="/menu">菜单管理</Menu.Item>
                    <Menu.Item key="/resource">资源管理</Menu.Item>
                </SubMenu>
            </Menu></Sider>
    );
}

export default DrawerLeft;