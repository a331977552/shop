import React, {Component} from 'react';
import {Layout} from "antd";
import {Route, Switch} from "react-router-dom";
import HomePage from "../home/HomePage";
import ProductListPage from "../product/ProductListPage";
import CategoryPage from "../category/CategoryPage";
import CategoryAddPage from "../category/CategoryAddPage";

const {Content} = Layout;

interface ContentProps {
}

class ContentMain extends Component<ContentProps, {}> {

    render() {

        return (
            <Content
                style={{
                    background: '#ffffff',
                    padding: 12,
                    margin: '10px',
                    minHeight: 280,
                }}
            >
                <Switch>
                    <Route path={"/product/product_list"} exact={true}>
                        <ProductListPage/>
                    </Route>

                    <Route path={"/product/category/add"} exact={true}>
                        <CategoryAddPage/>
                    </Route>
                    <Route path={"/product/category"} exact={true}>
                        <CategoryPage/>
                    </Route>

                    <Route path={"/"}>
                        <HomePage/>
                    </Route>

                </Switch>
            </Content>
        );
    }
}

export default ContentMain;