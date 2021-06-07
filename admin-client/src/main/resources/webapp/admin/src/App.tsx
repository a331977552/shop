import React, {useEffect} from 'react';
import {Button, Layout, Spin} from "antd";
import AppHeader from "./view/components/AppHeader";
import DrawerLeft from "./view/components/DrawerLeft";
import ContentMain from "./view/components/ContentMain";
import AppFooter from "./view/components/AppFooter";
import {useHistory} from "react-router-dom";
import {getUserInfo, selectUserReducer} from "./store/slices/userSlice";
import {getTokenFromStorage} from "./store/TokenConfig";
import {useAppDispatch, useAppSelector} from "./store/hooks";
import './index.css'

function App() {

    let history = useHistory();
    const dispatch = useAppDispatch();

    const user = useAppSelector(selectUserReducer);
    const token = getTokenFromStorage();

    useEffect(() => {
        if (token){
            dispatch(getUserInfo({}));
        }
    }, [dispatch,token])

    if (token === null) {
        history.push("/login");
        return <></>
    }


    function onRetryClick() {
        console.log("onRetryClick")
        dispatch(getUserInfo({}));
    }
    const status = user.status;

    return (
        <Spin tip={"loading..."} style={{height: '100vh', width: '100%'}}
              spinning={(status === 'loading')}>
            <Layout style={{height: '100vh', display: 'flex'}}>
                {status ===  'finished' && (<>
                    <DrawerLeft/>
                    <Layout>
                        <AppHeader id={123}/>
                        <ContentMain/>
                        <AppFooter/>
                    </Layout></>)
                }
                {status ===  'error' &&
                <div style={{
                    height: '100%', width: '100%', display: 'flex', flexDirection: 'column',
                    justifyContent: 'center', textAlign: "center",
                    alignItems: 'center'
                }}>
                    <h3>network error, please retry </h3>
                    <h4>{user.errorMsg}</h4>
                    <Button style={{width: '200px'}} onClick={onRetryClick}>retry</Button>
                </div>}
            </Layout>
        </Spin>)

}


export default App;
