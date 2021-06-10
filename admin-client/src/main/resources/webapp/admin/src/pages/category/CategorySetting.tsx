import React from 'react';
import {Button, Space, Switch} from "antd";
import {CategoryModel} from "../../model";
import {useAppDispatch} from "../../store/hooks";
import {getCategoryList,toggleVisible} from '../../store/slices/cateogrySlice';
import {useHistory} from "react-router-dom";

function CategorySetting(props: { record: CategoryModel }) {
    const {record} = props
    const history = useHistory();
    const dispatch = useAppDispatch();
    const onShowNextLevelClicked = () => {
        // history.push("category/"+ record.id,{})
        dispatch(getCategoryList({currentPage: 0, pageSize: 100, example: {parent: record.id}}));
    }


    const onVisibleChanged = () => {
        dispatch(toggleVisible(record))
    };

    return <Space>
                <Switch checkedChildren="显示" unCheckedChildren="隐藏" checked={record.visible}
                        onClick={onVisibleChanged}
                />
                <Button style={{padding:'0 3px' }} disabled={record.isleaf} onClick={onShowNextLevelClicked}
                        size={"small"}>{record.isleaf ? "没有下级" : "查看下级"}</Button>
            </Space>
}

export default CategorySetting;
