import React, {useState} from 'react';
import {Button, message, Popconfirm, Space} from "antd";
import {ProductAttrModel} from "../../model";
import {useAppDispatch} from "../../store/hooks";
import {useHistory} from "react-router-dom";
import {deleteProductAttrAPI} from "../../store/api/ProductAttrAPI";
import DeletionOperationHOC from "../../components/DeletionOperationHOC";
import { deleteProductAttrByIdLocally } from '../../store/slices/productAttrSlice';

function ProductAttrOperation(props: { record: ProductAttrModel }) {
    const dispatch = useAppDispatch();
    const history = useHistory();
    const onCategoryDeleteClicked = () => {
        return deleteProductAttrAPI(props.record.id).then(res => {
            message.success("删除成功!", 1)
            dispatch(deleteProductAttrByIdLocally(props.record));
        });
    }

    function onUpdate() {
        history.push("/product/attr/update/" + props.record.id)
    }

    return <DeletionOperationHOC id={props.record.id} onDelete={onCategoryDeleteClicked} onUpdateClick={onUpdate}/>
}


export default ProductAttrOperation;