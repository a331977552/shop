import React from 'react';
import DeletionOperationHOC from "../../components/DeletionOperationHOC";
import {useAppDispatch} from "../../store/hooks";

function DeliveryOperation() {
    let dispatch = useAppDispatch();
    function onDeleteClicked() {
    }

    function onUpdate() {

    }

    return (
        <DeletionOperationHOC onDelete={onDeleteClicked} onUpdateClick={onUpdate}/>
    );
}

export default DeliveryOperation;