import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import AppMain from './pages/AppMain';
import reportWebVitals from './reportWebVitals';
import 'antd/dist/antd.css';
import {addResponseTransformInterceptor, setTokenToCommonHeader} from "./http/HttpConfig";
import {
    BrowserRouter as Router,
    Switch,
    Route,
} from "react-router-dom";

import {Provider} from "react-redux";
import {store} from "./store/store";
import LoginPage from "./pages/LoginPage";

setTokenToCommonHeader();
addResponseTransformInterceptor();

ReactDOM.render(
    <React.StrictMode>
        <Provider store={store}>
            <Router>
                <Switch>
                    <Route  path={"/login"}>
                        <LoginPage/>
                    </Route>
                    <Route path={"*"}>
                        <AppMain/>
                    </Route>
                </Switch>
            </Router>
        </Provider>
    </React.StrictMode>,
    document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
