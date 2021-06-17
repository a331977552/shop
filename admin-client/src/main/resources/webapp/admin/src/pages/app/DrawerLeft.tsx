import React, {useState} from 'react';
import {Layout, Menu} from "antd";
import {LaptopOutlined, NotificationOutlined, UserOutlined, HomeOutlined} from "@ant-design/icons";
import {useHistory} from "react-router-dom";

const {Sider} = Layout;
const {SubMenu} = Menu;


function DrawerLeft() {
    const [collapsed, setCollapsed] = useState(false);
    let histroy = useHistory();
    const onCollapse = (collapsed: boolean) => {
        setCollapsed(collapsed);
    }

    const onMenuSelected = ({keyPath}: { item: any, key: string, keyPath: Array<string>, domEvent: any }) => {
        histroy.push( keyPath.reverse().join(""));
    };
    const pathArr = histroy.location.pathname.split("/");
    const defaultOpenKeys = pathArr.length>=2 && "/"+pathArr[1];
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
                defaultSelectedKeys={["/"+pathArr[pathArr.length-1]]}
                defaultOpenKeys={[defaultOpenKeys||"/product"]}
                onSelect={onMenuSelected}
            >
                <Menu.Item key="/" icon={<HomeOutlined/>}>主页</Menu.Item>
                <SubMenu key="/product" icon={<UserOutlined/>} title="商品">
                    <Menu.Item key="/list">商品列表</Menu.Item>
                    <Menu.Item key="/add">添加商品</Menu.Item>
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