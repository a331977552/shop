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
import ProductAddPage from "./product/ProductAddPage";

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
                <Route path={"/product/list"} exact={true}>
                    <ProductListPage/>
                </Route>
                <Route path={["/product/add"]} >
                    <ProductAddPage/>
                </Route>

                <Route path={"/product/category/add"} exact={true}>
                    <CategoryAddPage/>
                </Route>

                <Route path={["/product/spec/add", "/product/spec/update/:specID"]} exact={true}>
                    <ProductSpecAddOrUpdatePage/>
                </Route>

                <Route path={"/product/spec"} exact={true}>
                    <ProductSpecPage/>
                </Route>

                <Route path={"/product/attr"} exact={true}>
                    <ProductAttrPage/>
                </Route>
                <Route path={"/product/brand"} exact={true}>
                    <BrandListPage/>
                </Route>
                <Route path={["/product/brand/add","/product/brand/update/:bid"]} exact={true}>
                    <BrandAddOrUpdatePage/>
                </Route>
                <Route path={["/product/attr/add", "/product/attr/update/:attrID"]} exact={true}>
                    <ProductAttrAddOrUpdatePage/>
                </Route>
                <Route path={"/product/category/update/:cateID"} exact={true}>
                    <CategoryUpdatePage/>
                </Route>
                <Route path={"/product/category"} exact={true}>
                    <CategoryListPage/>
                </Route>
            </Switch>
        </StatusView>
    );
}

export default ProductBasePage;