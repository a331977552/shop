import React, { useState} from 'react';
import styled from 'styled-components'
import {Breadcrumb, Layout} from "antd";

import {useAppSelector} from "../../store/hooks";
import {selectUser} from "../../store/slices/userSlice";
import {useHistory,Link} from "react-router-dom";
import {KeyStr} from "../../model";

const {Header} = Layout;

const StyledSpan = styled.span`
  cursor: pointer;
  align-self: center;
  height: 30px;
  line-height: 30px;
  text-decoration: underline;
`

const breadCrumbMap:KeyStr = {
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
    "":'主页',

}
function calcBread(pathname:string){
    let pathArray = pathname.split("/");
    if (pathArray.length >= 4 && pathArray[pathArray.length-2] == 'update'){
        pathArray = pathArray.slice(0,pathArray.length-1);
    }
    const breadCrumbList: { name:string,uipath:string }[]= [];
    while(pathArray.length>0){
        breadCrumbList.unshift({name:breadCrumbMap[pathArray.join("/")],uipath:pathArray.join("/")});
        pathArray = pathArray.slice(0,pathArray.length-1);
    }
    return breadCrumbList;
}

const AppHeader = () => {
    let history = useHistory();
    const [breadCrumbList,setBreadCrumbList] = useState<{ name:string,uipath:string }[]>(()=>calcBread(history.location.pathname));
    history.listen((location)=>{
        setBreadCrumbList(calcBread(history.location.pathname));
    })
    const user = useAppSelector(selectUser)?.result;
    return (
        <Header style={{display: 'flex', backgroundColor: 'white', height: '50px', padding: '0 30px'}}>
            <div style={{alignSelf: "center"}}>
                <Breadcrumb>
                    {breadCrumbList?.map((item,index)=>
                        <Breadcrumb.Item key={item.uipath}>{index+1 === breadCrumbList.length?item.name:
                           <Link to={item.uipath}>{item.name}</Link>
                        }</Breadcrumb.Item>)}
                </Breadcrumb>
            </div>
            <div style={{flex: '1 0 0px'}}/>
            <div style={{display: 'flex'}}>
                <StyledSpan> {user?.alias}</StyledSpan>
            </div>
        </Header>
    );
};
export default AppHeader;

//
// const mapStateToProps = (state: RootState) => ({
//
// })
//
// const mapDispatch = {
//     toggleOn: () => ({ type: 'TOGGLE_IS_ON' }),
// }
//
//
// const connector = connect(mapStateToProps, mapDispatch)
//
// // The inferred type will look like:
// // {isOn: boolean, toggleOn: () => void}
// type PropsFromRedux = ConnectedProps<typeof connector>
//
// interface Props extends PropsFromRedux{
//     id:number
// }

