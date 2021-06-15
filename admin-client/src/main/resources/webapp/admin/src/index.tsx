import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import 'antd/dist/antd.css';
import {addRequestInterceptor, addResponseTransformInterceptor} from "./store/HttpConfig";
import {
    BrowserRouter as Router,
    Switch,
    Route,
} from "react-router-dom";
import { LoadingOutlined } from '@ant-design/icons';
import {Provider} from "react-redux";
import {store} from "./store/store";
import LoginPage from "./pages/login/LoginPage";
import {Spin} from "antd";

addRequestInterceptor();
addResponseTransformInterceptor();

const antIcon = <LoadingOutlined style={{ fontSize: 24 }} spin />;
Spin.setDefaultIndicator(antIcon);

function Index() {

    return <Switch>
        <Route path={"/login"}>
            <LoginPage/>
        </Route>
        <Route path={"*"}>
            <App/>
        </Route>
    </Switch>

}

ReactDOM.render(
        <Provider store={store}>
            <Router>
                <Index/>
            </Router>
        </Provider>,
    document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
