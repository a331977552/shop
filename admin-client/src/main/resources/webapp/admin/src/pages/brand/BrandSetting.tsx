import React from 'react';
import {BrandModel} from "../../model";


function BrandSetting(brandModel:BrandModel) {
    return (
        <div>{brandModel.name}</div>
    );
}

export default BrandSetting;