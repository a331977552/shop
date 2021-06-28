import React from 'react';
import StatusView from "../components/StatusView";
import {Route, Switch} from "react-router-dom";
import OrderListPage from "./order/OrderListPage";

export default function OrderSectionBasePage() {

    return (
        <StatusView status={'finished'} >
            <Switch>
                <Route path={"/order"} exact={true}>
                    <OrderListPage/>
                </Route>
            </Switch>
        </StatusView>
    );
}

