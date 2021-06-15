import React from 'react';
import DeletionOperationHOC from "../../components/DeletionOperationHOC";
import {BrandModel} from "../../model";

function BrandOperation(props:BrandModel) {
    function onBrandDeleteClicked() {

    }

    function onUpdate() {

    }

    return (
        <DeletionOperationHOC id={props.id} onDelete={onBrandDeleteClicked} onUpdateClick={onUpdate}/>
    );
}

export default BrandOperation;