import React from 'react';
import { message} from "antd";
import {ProductSpecModel} from "../../model";
import {useAppDispatch} from "../../store/hooks";
import {useHistory} from "react-router-dom";
import {deleteProductSpecAPI} from "../../api/ProductSpecAPI";
import {deleteProductSpecByIdLocally} from '../../store/slices/productSpecSlice';
import DeletionOperationHOC from "../../components/DeletionOperationHOC";

function ProductSpecOperation(props: { record: ProductSpecModel }) {
    const dispatch = useAppDispatch();
    const history = useHistory();
    const onCategoryDeleteClicked = () => {
        return deleteProductSpecAPI(props.record.id).then(res => {
            message.success("删除成功!", 1)
            dispatch(deleteProductSpecByIdLocally(props.record));
        });
    }

    const onUpdateClick = () => {
        history.push("/product/spec/update/" + props.record.id)
    };

    return <DeletionOperationHOC onDelete={onCategoryDeleteClicked} onUpdateClick={onUpdateClick}/>
}

export default ProductSpecOperation;
