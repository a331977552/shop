import React, {Component} from 'react';
import {Layout} from "antd";
import {Route, Switch} from "react-router-dom";
import HomePage from "../home/HomePage";
import ProductBasePage from "../ProductBasePage";

const {Content} = Layout;


function ContentMain() {
    console.log("contentMain")
    return (
        <Content
            style={{
                background: '#ffffff',
                padding: 12,
                margin: '10px',
                minHeight: 280,
                overflow:'auto'
            }}
        >
            <Switch>
                <Route path={[
                    "/product","/product/**",
                    "/category","/category/**",
                    "/brand",'/brand/**',
                    "/spec",'/spec/**',
                    "/attr",'/attr/**']} >
                    <ProductBasePage/>
                </Route>
                <Route path={"/"}>
                    <HomePage/>
                </Route>

            </Switch>
        </Content>
    );
}

export default ContentMain;