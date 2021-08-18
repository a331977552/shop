import React from 'react';
import StatusView from "../components/StatusView";
import {Route, Switch} from "react-router-dom";
import OrderListPage from "./order/list/OrderListPage";
import OrderDetailPage from "./order/detail/OrderDetailPage";

export default function OrderSectionBasePage() {

    return (
        <StatusView status={'finished'} >
            <Switch>
                <Route path={"/order/detail"} exact={true}>
                    <OrderDetailPage/>
                </Route>
                <Route path={"/order"} exact={true}>
                    <OrderListPage/>
                </Route>
            </Switch>
        </StatusView>
    );
}

