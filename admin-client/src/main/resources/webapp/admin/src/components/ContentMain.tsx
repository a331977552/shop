import React, {Component} from 'react';
import {Layout} from "antd";
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
                Content
            </Content>
        );
    }
}

export default ContentMain;