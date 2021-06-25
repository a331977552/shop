import React, {useEffect} from 'react';
import { Layout} from "antd";
import AppHeader from "./pages/app/AppHeader";
import LeftDrawer from "./pages/app/LeftDrawer";
import ContentMain from "./pages/app/ContentMain";
import AppFooter from "./pages/app/AppFooter";
import {getUserInfo, selectUserReducer} from "./store/slices/userSlice";
import {useAppDispatch, useAppSelector} from "./store/hooks";
import './index.css'
import StatusView from "./components/StatusView";

function App() {

    const dispatch = useAppDispatch();
    const user = useAppSelector(selectUserReducer);
    const {status, errorMsg} = user;

    function onRetryClick() {
            dispatch(getUserInfo(null));
    }

    useEffect(() => {
        dispatch(getUserInfo(null));
    }, [dispatch])


    return (
        <StatusView status={status} errorMsg={errorMsg} retry={onRetryClick}>
            <Layout style={{height: '100vh', display: 'flex'}}>
                <LeftDrawer/>
                <Layout>
                    <AppHeader/>
                    <ContentMain/>
                    <AppFooter/>
                </Layout>
            </Layout>
        </StatusView>)

}


export default App;
