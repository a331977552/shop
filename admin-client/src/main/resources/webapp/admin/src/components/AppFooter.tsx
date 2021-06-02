import React from 'react';
import {Layout} from "antd";
const {Footer} = Layout;
interface FooterProps {

}

function AppFooter(props:FooterProps) {
    return (
        <Footer style={{textAlign: 'center'}}>Ant Design ©2018 Created by Ant UED</Footer>
    );
}

export default AppFooter;