import React from 'react';
import {message} from "antd";
import {ProductModel, RouterState} from "../../../model";
import {useAppDispatch} from "../../../store/hooks";
import {useHistory} from "react-router-dom";
import DeletionOperationHOC from "../../../components/DeletionOperationHOC";
import {deleteProductAPI} from "../../../api";
import { deleteProductByIdLocally } from '../../../store/slices/productSlice';

function ProductOperation(record: ProductModel) {
    const dispatch = useAppDispatch();
    const history = useHistory<RouterState>();
    const onProductDeleteClicked = () => {
        return deleteProductAPI(record.id).then(res => {
            message.success("删除成功!", 1)
            dispatch(deleteProductByIdLocally(record));
        });
    }

    function onUpdate() {
        history.push({pathname:"/product/update/" +record.id,state:{updateMenu:true}})
    }

    return <DeletionOperationHOC  onDelete={onProductDeleteClicked} onUpdateClick={onUpdate}/>
}


export default ProductOperation;