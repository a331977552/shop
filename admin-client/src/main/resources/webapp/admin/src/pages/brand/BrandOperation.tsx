import React from 'react';
import DeletionOperationHOC from "../../components/DeletionOperationHOC";
import {BrandModel} from "../../model";
import {useHistory} from "react-router-dom";
import {deleteBrandAPI} from "../../api";
import {message} from "antd";
import {deleteBrand} from "../../store/slices/brandSlice";
import {useAppDispatch} from "../../store/hooks";

function BrandOperation(props: BrandModel) {
    let history = useHistory();
    let dispatch = useAppDispatch();
    function onBrandDeleteClicked() {
        return deleteBrandAPI(props.id).then(() => {
            message.success("删除成功!", 1)
            dispatch(deleteBrand(props.id));
        })
    }

    function onUpdate() {
        history.push("/product/brand/update/" + props.id)
    }

    return (
        <DeletionOperationHOC onDelete={onBrandDeleteClicked} onUpdateClick={onUpdate}/>
    );
}

export default BrandOperation;