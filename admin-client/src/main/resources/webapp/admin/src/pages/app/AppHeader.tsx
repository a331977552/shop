import React, {useState} from 'react';
import styled from 'styled-components'
import {Breadcrumb, Layout, Dropdown, Menu} from "antd";

import {useAppSelector} from "../../store/hooks";
import {selectUser} from "../../store/slices/userSlice";
import {useHistory, Link} from "react-router-dom";
import {KeyStr} from "../../model";
import {removeTokenFromStorage} from "../../store/TokenConfig";

const {Header} = Layout;

const StyledSpan = styled.span`
  cursor: pointer;
  align-self: center;
  height: 30px;
  line-height: 30px;
  text-decoration: underline;
`

const breadCrumbMap: KeyStr = {
    "/product": '商品列表',
    "/product/add": '添加',
    "/product/update": '更新',

    "/category": '目录',
    "/category/add": '目录添加',
    "/category/update": '目录更新',

    "/attr": '属性列表',
    "/attr/add": '属性添加',
    "/attr/update": '属性更新',

    "/spec": '规格列表',
    "/spec/add": '规格添加',
    "/spec/update": '规格更新',

    "/brand": '品牌列表',
    "/brand/add": '品牌添加',
    "/brand/update": '品牌更新',

    "/order":'订单列表',
    "/order/detail":'订单详情',
    "/order/shipping":'发货',
    "": '主页',
}

function calcBread(pathname: string) {
    let pathArray = pathname.split("/");
    if (pathArray.length >= 4 && pathArray[pathArray.length - 2] === 'update') {
        pathArray = pathArray.slice(0, pathArray.length - 1);
    }
    const breadCrumbList: { name: string, uipath: string }[] = [];
    while (pathArray.length > 0) {
        breadCrumbList.unshift({name: breadCrumbMap[pathArray.join("/")], uipath: pathArray.join("/")});
        pathArray = pathArray.slice(0, pathArray.length - 1);
    }
    return breadCrumbList;
}

const AppHeader = () => {
    let history = useHistory();
    const [breadCrumbList, setBreadCrumbList] = useState<{ name: string, uipath: string }[]>(() => calcBread(history.location.pathname));
    history.listen((location) => {
        setBreadCrumbList(calcBread(history.location.pathname));
    });
    const user = useAppSelector(selectUser)?.result;

    function loginOut() {
        removeTokenFromStorage();
        history.push("/login");
    }
    if (!user)
        return null;



    return (
        <Header style={{display: 'flex', backgroundColor: 'white', height: '50px', padding: '0 30px'}}>
            <div style={{alignSelf: "center"}}>
                <Breadcrumb>
                    {breadCrumbList?.map((item, index) =>
                        <Breadcrumb.Item key={item.uipath}>{index + 1 === breadCrumbList.length ? item.name :
                            <Link to={{pathname: item?.uipath || "/", state: {updateMenu: true}}}>{item.name}</Link>
                        }</Breadcrumb.Item>)}
                </Breadcrumb>
            </div>
            <div style={{flex: '1 0 0px'}}/>
            <div style={{display: 'flex'}}>
                <Dropdown trigger={['click']}  overlay={
                    <Menu>
                        <Menu.Item key="0" >
                            {user.alias}
                        </Menu.Item>
                        <Menu.Divider />
                        <Menu.Item key="1" onClick={loginOut}>注销</Menu.Item>
                    </Menu>
                }>
                    <StyledSpan> {user.alias}</StyledSpan>
                </Dropdown>
            </div>
        </Header>
    );
};
export default AppHeader;

