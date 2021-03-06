import React, {useState} from 'react';
import {Layout, Menu} from "antd";
import {LaptopOutlined, NotificationOutlined, UserOutlined, HomeOutlined} from "@ant-design/icons";
import {useHistory} from "react-router-dom";
import {KeyStr, RouterState} from "../../model";

const {Sider} = Layout;
const {SubMenu} = Menu;

const proArray: KeyStr = {
    "product": '/pro',
    "category": '/pro',
    "brand": '/pro',
    "spec": '/pro',
    "attr": '/pro',
    "order":'/or',
};

const pathMenuMap: KeyStr = {
    // "/order/detail":'/order',
};

function getMenuPath(pathArr: string[]) {

    let selectedMenuKey = '/';
    if (pathArr.length >= 2) {
        selectedMenuKey =  "/" + pathArr.slice(1).join("/");
    }
    return pathMenuMap[selectedMenuKey]||selectedMenuKey;
}

function getOpenPath(pathArr: string[]):string {
    let openKey = "/";
    if (pathArr.length >= 2) {
        openKey = proArray[pathArr[1]] || '/'
    }
    return openKey;
}

function LeftDrawer() {
    const [collapsed, setCollapsed] = useState(false);
    const history = useHistory<RouterState>();
    const pathArr = history.location.pathname.split("/");

    const [selectedMenuKey,setSelectedMenuKey] = useState<string>(()=>getMenuPath(pathArr));
    const [openPath] = useState<string>(()=>getOpenPath(pathArr));
    const onCollapse = (collapsed: boolean) => {
        setCollapsed(collapsed);
    }

    history.listen((location)=>{
       if(location.state?.updateMenu){
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
                <Menu.Item key="/" icon={<HomeOutlined/>}>??????</Menu.Item>
                <SubMenu key="/pro" icon={<UserOutlined/>} title="??????">
                    <Menu.Item key="/product">????????????</Menu.Item>
                    <Menu.Item key="/product/add">????????????</Menu.Item>
                    <Menu.Item key="/category">????????????</Menu.Item>
                    <Menu.Item key="/brand">????????????</Menu.Item>
                </SubMenu>
                <SubMenu key="/or" icon={<LaptopOutlined/>} title="??????">
                    <Menu.Item key="/order">????????????</Menu.Item>
                    <Menu.Item key="/setting">????????????</Menu.Item>
                    <Menu.Item key="/refund">????????????</Menu.Item>
                    <Menu.Item key="/refund_reason">??????????????????</Menu.Item>
                </SubMenu>
                <SubMenu key="/operation" icon={<NotificationOutlined/>} title="??????">
                    <Menu.Item key="/flashSaleList">????????????</Menu.Item>
                    <Menu.Item key="/coupon">?????????</Menu.Item>
                    <Menu.Item key="/productRecom">????????????</Menu.Item>
                    <Menu.Item key="/brandRecom">????????????</Menu.Item>
                </SubMenu>
                <SubMenu key="/auth" icon={<NotificationOutlined/>} title="??????">
                    <Menu.Item key="/user">????????????</Menu.Item>
                    <Menu.Item key="/role">????????????</Menu.Item>
                    <Menu.Item key="/menu">????????????</Menu.Item>
                    <Menu.Item key="/resource">????????????</Menu.Item>
                </SubMenu>
            </Menu></Sider>
    );
}

export default LeftDrawer;