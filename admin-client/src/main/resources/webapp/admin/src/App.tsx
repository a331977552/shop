import React, {useEffect} from 'react';
import { Layout} from "antd";
import AppHeader from "./pages/app/AppHeader";
import DrawerLeft from "./pages/app/DrawerLeft";
import ContentMain from "./pages/app/ContentMain";
import AppFooter from "./pages/app/AppFooter";
import {useHistory} from "react-router-dom";
import {getUserInfo, selectUserReducer} from "./store/slices/userSlice";
import {getTokenFromStorage} from "./store/TokenConfig";
import {useAppDispatch, useAppSelector} from "./store/hooks";
import './index.css'
import StatusView from "./components/StatusView";

function App() {

    const history = useHistory();
    const dispatch = useAppDispatch();
    const user = useAppSelector(selectUserReducer);

    function onRetryClick() {
        const token = getTokenFromStorage();
        if (token === null) {
            history.push("/login");
        } else if (token) {
            dispatch(getUserInfo(null));
        }
    }

    useEffect(() => {
        const token = getTokenFromStorage();
        if (token) {
            dispatch(getUserInfo(null));
        }
    }, [dispatch])

    return (
        <StatusView status={user.status} retry={onRetryClick}>
            <Layout style={{height: '100vh', display: 'flex'}}>
                <DrawerLeft/>
                <Layout>
                    <AppHeader/>
                    <ContentMain/>
                    <AppFooter/>
                </Layout>
            </Layout>
        </StatusView>)

}


export default App;
