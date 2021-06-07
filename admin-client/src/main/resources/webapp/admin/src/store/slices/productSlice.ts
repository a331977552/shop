import {
    createAsyncThunk
} from '@reduxjs/toolkit';
import ResultModel from "../../model/ResultModel";
import {ErrorModel} from "../../model/ErrorModel";
import {RootState} from "../store";
import { createGenericSlice, GenericState} from "../hooks";
import ProductListModel from "../../model/ProductListModel";
import {getProductListAPI} from "../api";

const initialState: GenericState<ProductListModel> = {
    status: 'loading'
};


export const getProductList = createAsyncThunk<ResultModel<ProductListModel>, {}, ErrorModel>
(
    'product/list',
    async ({}, {rejectWithValue}) => {
        try {
            return await getProductListAPI();
        } catch (errorResult) {
            return rejectWithValue(errorResult);
        }
    },
);

export const productSlice = createGenericSlice({
    name: 'product',
    initialState: initialState, reducers: {
    },
    extraReducers: (builder) => {
        builder
            .addCase(getProductList.pending, (state, action) => {
                state.status = 'loading';
            })

            .addCase(getProductList.rejected, (state, action) => {
                state.status = 'error';
                state.errorMsg = action.payload?.msgDetail;
            })
            .addCase(getProductList.fulfilled, (state, action) => {
                console.log(action)
                return {status: "finished", data: action.payload.result};
            })
    }
})

export const selectProductReducer = (state: RootState) => state.product;

export const {} = productSlice.actions;
export default productSlice.reducer;
