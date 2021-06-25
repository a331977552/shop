import React from 'react';
import {message} from "antd";
import {ProductAttrModel, RouterState} from "../../model";
import {useAppDispatch} from "../../store/hooks";
import {useHistory} from "react-router-dom";
import {deleteProductAttrAPI} from "../../api/ProductAttrAPI";
import DeletionOperationHOC from "../../components/DeletionOperationHOC";
import { deleteProductAttrByIdLocally } from '../../store/slices/productAttrSlice';

function ProductAttrOperation(props: { record: ProductAttrModel }) {
    const dispatch = useAppDispatch();
    const history = useHistory<RouterState>();
    const onCategoryDeleteClicked = () => {
        return deleteProductAttrAPI(props.record.id).then(res => {
            message.success("删除成功!", 1)
            dispatch(deleteProductAttrByIdLocally(props.record));
        });
    }

    function onUpdate() {
        history.push({pathname:"/attr/update/" +props.record.id,state:{updateMenu:false}})
    }

    return <DeletionOperationHOC  onDelete={onCategoryDeleteClicked} onUpdateClick={onUpdate}/>
}


export default ProductAttrOperation;