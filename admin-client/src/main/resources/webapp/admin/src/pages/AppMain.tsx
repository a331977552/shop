import React, {useEffect} from 'react';
import {Button, Layout, Spin} from "antd";
import AppHeader from "../components/AppHeader";
import DrawerLeft from "../components/DrawerLeft";
import ContentMain from "../components/ContentMain";
import AppFooter from "../components/AppFooter";
import {useHistory} from "react-router-dom";
import {getToken} from "../http/TokenConfig";
import {getUserInfo, selectUser} from "../store/user/UserSlice";
import {useAppDispatch, useAppSelector} from "../store/hooks";


function AppMain() {
    let history = useHistory();
    let token = getToken();
    const dispatch = useAppDispatch();
    const user = useAppSelector(selectUser);
    const status = useAppSelector((state) => state.userReducer.userStatus);
    useEffect(() => {
        dispatch(getUserInfo({}));
    }, []);

    function onRetryClick() {
        dispatch(getUserInfo({}));
    }

    if (!token) {
        history.push("/login");
    }

    return (
        (status.status == 'failed') ?
            <div>loading user error, please check network
                <Button onClick={onRetryClick}>retry</Button>
            </div> :
            <Spin tip={"loading user info"} spinning={!user}>
                <Layout style={{height: '100vh', display: 'flex'}}>
                    <DrawerLeft/>
                    <Layout>
                        <AppHeader id={123}/>
                        <ContentMain/>
                        <AppFooter/>
                    </Layout>
                </Layout>
            </Spin>
    );

}


export default AppMain;
