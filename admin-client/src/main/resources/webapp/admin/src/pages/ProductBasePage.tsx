import React, {useEffect} from 'react';
import {Route, Switch} from "react-router-dom";
import ProductListPage from "./product/ProductListPage";
import CategoryAddPage from "./category/CategoryAddPage";
import ProductSpecAddOrUpdatePage from "./spec/ProductSpecAddOrUpdatePage";
import ProductSpecPage from "./spec/ProductSpecPage";
import CategoryUpdatePage from "./category/CategoryUpdatePage";
import CategoryListPage from "./category/CategoryListPage";
import {useAppDispatch, useAppSelector} from "../store/hooks";
import {getCategoryList, selectCategoryReducer} from "../store/slices/cateogrySlice";
import StatusView from "./../components/StatusView";
import ProductAttrPage from "./attr/ProductAttrPage";
import ProductAttrAddOrUpdatePage from "./attr/ProductAttrAddOrUpdatePage";
import BrandListPage from "./brand/BrandListPage";
import BrandAddOrUpdatePage from "./brand/BrandAddOrUpdatePage";
import ProductAddPage from "./product/add/ProductAddPage";
import ProductUpdatePage from "./product/update/ProductUpdatePage";

function ProductBasePage() {
    let dispatch = useAppDispatch();
    let categoryReducer = useAppSelector(selectCategoryReducer);
    useEffect(() => {
        dispatch(getCategoryList())
    }, [dispatch]);

    function retry() {
        dispatch(getCategoryList())
    }

    return (
        <StatusView status={categoryReducer.categoryList.status} retry={retry}>
            <Switch>
                <Route path={"/product"} exact={true}>
                    <ProductListPage/>
                </Route>
                <Route path={["/product/add"]} >
                    <ProductAddPage/>
                </Route>

                <Route path={["/product/update/:pid"]} >
                    <ProductUpdatePage/>
                </Route>

                <Route path={"/category"} exact={true}>
                    <CategoryListPage/>
                </Route>
                <Route path={"/category/add"} exact={true}>
                    <CategoryAddPage/>
                </Route>
                <Route path={"/category/update/:cateID"} exact={true}>
                    <CategoryUpdatePage/>
                </Route>

                <Route path={["/spec/add", "/spec/update/:specID"]} exact={true}>
                    <ProductSpecAddOrUpdatePage/>
                </Route>

                <Route path={"/spec"} exact={true}>
                    <ProductSpecPage/>
                </Route>

                <Route path={"/attr"} exact={true}>
                    <ProductAttrPage/>
                </Route>
                <Route path={"/brand"} exact={true}>
                    <BrandListPage/>
                </Route>
                <Route path={["/brand/add","/brand/update/:bid"]} exact={true}>
                    <BrandAddOrUpdatePage/>
                </Route>
                <Route path={["/attr/add", "/attr/update/:attrID"]} exact={true}>
                    <ProductAttrAddOrUpdatePage/>
                </Route>


            </Switch>
        </StatusView>
    );
}

export default ProductBasePage;