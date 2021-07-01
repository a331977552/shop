import React from 'react';
import {OrderModel} from "../../../model";
import {useHistory} from "react-router-dom";
import {parseSearchParams} from "../../../services";

function OrderShippingPage() {
    let history = useHistory();
    let search = history.location.search;
    let params = parseSearchParams(search);

    return (
        <div>{params['oid']}</div>
    );
}

export default OrderShippingPage;