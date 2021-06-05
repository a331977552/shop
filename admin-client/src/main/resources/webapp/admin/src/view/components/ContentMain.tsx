import React, {Component} from 'react';
import {Layout} from "antd";
import {Route, Switch} from "react-router-dom";
import HomePage from "../pages/HomePage";
const {Content} = Layout;
interface ContentProps {
}

class ContentMain extends Component<ContentProps,{}> {

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
                    <Route path={"/"}>
                        <HomePage/>
                    </Route>

                </Switch>
                Content
            </Content>
        );
    }
}

export default ContentMain;