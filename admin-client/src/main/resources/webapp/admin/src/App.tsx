import React, {useEffect} from 'react';
import {Button, Layout, Spin} from "antd";
import AppHeader from "./pages/app/AppHeader";
import DrawerLeft from "./pages/app/DrawerLeft";
import ContentMain from "./pages/app/ContentMain";
import AppFooter from "./pages/app/AppFooter";
import {useHistory} from "react-router-dom";
import {getUserInfo, selectUserReducer} from "./store/slices/userSlice";
import {getTokenFromStorage} from "./store/TokenConfig";
import {useAppDispatch, useAppSelector} from "./store/hooks";
import './index.css'

function App() {

    const history = useHistory();
    const dispatch = useAppDispatch();
    const user = useAppSelector(selectUserReducer);
    const code = user.data?.code;

    function loadUserOrRedirect() {
        const token = getTokenFromStorage();
        if (token === null) {
            history.push("/login");
        } else if (token) {
            dispatch(getUserInfo(null));
        }
    }
    useEffect(() => {
        loadUserOrRedirect();
    }, [dispatch, history])


    const status = user.status;
    if (code === 401) {
        history.push("/login");
    }
    return (
        <Spin tip={"loading..."} style={{height: '100vh', width: '100%'}}
              spinning={(status === 'loading')}>
            <Layout style={{height: '100vh', display: 'flex'}}>
                {status === 'finished' && (<>
                    <DrawerLeft/>
                    <Layout>
                        <AppHeader/>
                        <ContentMain/>
                        <AppFooter/>
                    </Layout></>)
                }
                {status === 'error' &&
                <div style={{
                    height: '100%', width: '100%', display: 'flex', flexDirection: 'column',
                    justifyContent: 'center', textAlign: "center",
                    alignItems: 'center'
                }}>
                    <h3>network error, please retry </h3>
                    <h4>{user.errorMsg}</h4>
                    <Button style={{width: '200px'}} onClick={loadUserOrRedirect}>retry</Button>
                </div>}
            </Layout>
        </Spin>)

}


export default App;
