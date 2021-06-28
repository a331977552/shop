import React, {useState} from 'react';
import {Button} from "antd";
import CentredLoading from "./CentredLoading";

interface Loading {
    status: "loading" | "error" | "finished"
    children: JSX.Element|null,
    retry?: React.MouseEventHandler<HTMLElement>,
    errorMsg?: string,
    loadOnce?: boolean
}

function StatusView(props: Loading) {
    const [loadOnce, setLoadOnce] = useState(false);

    if (loadOnce){
        return props.children;
    }
    else if (props.status === 'error') {
        return <div style={{
            width: '100%',
            height: '100%',
            display: 'flex',
            justifyContent: "center",
            alignItems: 'center'
        }}>
            <Button onClick={props.retry}>网络异常,请重试</Button>
        </div>

    } else if (props.status === 'loading') {
        return <CentredLoading/>;
    } else {
        if (props.loadOnce) {
            setLoadOnce(true)
        }
        return props.children;
    }
}

export default StatusView;