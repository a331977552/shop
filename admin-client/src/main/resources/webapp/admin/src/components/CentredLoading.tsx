import React from 'react';
import {Spin} from "antd";

function CentredLoading() {
    return (
        <div style={{
            width: '100%',
            height: '100%',
            display: 'flex',
            justifyContent: "center",
            alignItems: 'center'
        }}>
            <Spin tip={"loading..."}>
            </Spin>
        </div>
    );
}

export default CentredLoading;