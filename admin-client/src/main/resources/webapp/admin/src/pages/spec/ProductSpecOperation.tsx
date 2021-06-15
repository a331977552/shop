import React, {useState} from 'react';
import {Button, message, Popconfirm, Space} from "antd";
import {ProductSpecModel} from "../../model";
import {useAppDispatch} from "../../store/hooks";
import {useHistory} from "react-router-dom";
import {deleteProductSpecAPI} from "../../store/api/ProductSpecAPI";
import {deleteProductSpecByIdLocally} from '../../store/slices/productSpecSlice';

function ProductSpecOperation(props: { record: ProductSpecModel }) {
    const [loading, setLoading] = useState(false);
    const [visible, setVisible] = useState(false);
    const dispatch = useAppDispatch();
    const history = useHistory();
    const onCategoryDeleteClicked = () => {
        setLoading(true);
        setVisible(true);
        deleteProductSpecAPI(props.record.id).then(res => {
            message.success("删除成功!", 1)
            dispatch(deleteProductSpecByIdLocally(props.record));
        }).catch(err => {
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

    const onUpdateClick = () => {
        history.push("/product/spec/update/" + props.record.id)
    };

    return <Space size={"small"}>
        <Popconfirm title={"确认要删除吗?"} visible={visible} okButtonProps={{loading:loading}}
                    onConfirm={onCategoryDeleteClicked} onCancel={onCancelClick}>
            <Button size={"small"}
                    type={'link'}
                    onClick={onDeleteClicked}
                    style={{padding: '0px'}}>删除</Button>
        </Popconfirm>
        <Button size={"small"}
                type={'link'}
                onClick={onUpdateClick}
                style={{padding: '0px'}}>更新</Button></Space>
}

export default ProductSpecOperation;
