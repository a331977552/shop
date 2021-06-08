import React from 'react';
import {Button, Switch} from "antd";
import {CategoryModel} from "../../model";
import {useAppDispatch} from "../../store/hooks";
import {getCategoryList,toggleVisible} from '../../store/slices/cateogrySlice';

function CategorySetting(props: { record: CategoryModel }) {
    const {record} = props
    const dispatch = useAppDispatch();
    const onShowNextLevelClicked = () => {
        dispatch(getCategoryList({currentPage: 0, pageSize: 20, example: {parent: record.id}}));
    }

    const onVisibleChanged = () => {
        dispatch(toggleVisible(record))
    };

    return <span>
                <Switch checkedChildren="显示" unCheckedChildren="隐藏" checked={record.visible}
                        onClick={onVisibleChanged}
                />
                <Button style={{marginLeft: '10px'}} disabled={record.isleaf} onClick={onShowNextLevelClicked}
                        size={"small"}>{record.isleaf ? "没有下级" : "查看下级"}</Button>
            </span>
}

export default CategorySetting;
