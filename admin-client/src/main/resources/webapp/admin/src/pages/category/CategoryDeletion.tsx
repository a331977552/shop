import React, {useState} from 'react';
import {Button, message, Popconfirm, Space} from "antd";
import {deleteCategoryAPI} from "../../store/api/CategoryAPI";
import {CategoryModel} from "../../model";
import {useAppDispatch} from "../../store/hooks";
import {deleteCategoryByID} from '../../store/slices/cateogrySlice';

function CategoryDeletion(props: { record: CategoryModel }) {
    const [loading, setLoading] = useState(false);
    const [visible, setVisible] = useState(false);
    const dispatch = useAppDispatch();
    const onCategoryDeleteClicked = () => {
        setLoading(true);
        setVisible(true);
        deleteCategoryAPI(props.record.id).then(res => {
            message.success("删除成功!", 1)
            dispatch(deleteCategoryByID(props.record));
        }).catch(err => {
            setLoading(false);
            message.error(err.msgDetail, 3)
        });


    }

    function onDeleteClicked() {
        setVisible(true);
    }

    function onCancelClick() {
        setVisible(false)
    }

    return <Space size={"small"}>
        <Popconfirm title={"确认要删除吗?"} visible={visible} okButtonProps={{loading}}
                    onConfirm={onCategoryDeleteClicked} onCancel={onCancelClick}>
            <Button size={"small"}
                    onClick={onDeleteClicked}
                    type={'link'}>删除</Button></Popconfirm>
        <Button size={"small"} type={'link'}>更新</Button></Space>
}

export default CategoryDeletion;
