import React, {useState} from 'react';
import {Button, message, Popconfirm, Space} from "antd";

function DeletionOperationHOC(props: { onDelete: Function, onUpdateClick: React.MouseEventHandler<HTMLElement> }) {
    const [loading, setLoading] = useState(false);
    const [visible, setVisible] = useState(false);
    const onCategoryDeleteClicked = () => {
        setLoading(true);
        setVisible(true);
        props.onDelete().catch((err: any) => {
            setLoading(false);
            setVisible(false);
            message.error(err.msgDetail, 3)
        });
    }

    const onDeleteClicked = () => {
        setVisible(true);
    };

    const onCancelClick = () => {
        setVisible(false)
    };

    return <Space size={"small"}>
        <Popconfirm title={"确认要删除吗?"} visible={visible} okButtonProps={{loading: loading}}
                    onConfirm={onCategoryDeleteClicked} onCancel={onCancelClick}>
            <Button size={"small"}
                    type={'link'}
                    onClick={onDeleteClicked}
                    style={{padding: '0px'}}>删除</Button>
        </Popconfirm>
        <Button size={"small"}
                type={'link'}
                onClick={props.onUpdateClick}
                style={{padding: '0px'}}>更新</Button></Space>
}


export default DeletionOperationHOC;