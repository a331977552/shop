import React from 'react';
import { message} from "antd";
import {ProductSpecModel, RouterState} from "../../model";
import {useAppDispatch} from "../../store/hooks";
import {useHistory} from "react-router-dom";
import {deleteProductSpecAPI} from "../../api/ProductSpecAPI";
import {deleteProductSpecByIdLocally} from '../../store/slices/productSpecSlice';
import DeletionOperationHOC from "../../components/DeletionOperationHOC";

function ProductSpecOperation(props: { record: ProductSpecModel }) {
    const dispatch = useAppDispatch();
    const history = useHistory<RouterState>();
    const onCategoryDeleteClicked = () => {
        return deleteProductSpecAPI(props.record.id).then(res => {
            message.success("删除成功!", 1)
            dispatch(deleteProductSpecByIdLocally(props.record));
        });
    }

    const onUpdateClick = () => {

        history.push( {pathname:"/spec/update/" + props.record.id,state:{updateMenu:true}})
    };

    return <DeletionOperationHOC onDelete={onCategoryDeleteClicked} onUpdateClick={onUpdateClick}/>
}

export default ProductSpecOperation;
