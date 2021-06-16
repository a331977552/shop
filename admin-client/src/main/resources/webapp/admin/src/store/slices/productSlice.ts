import {
    createAsyncThunk
} from '@reduxjs/toolkit';
import {ErrorModel, ResultModel, ProductModel, PageModel, PageQueryModel, ProductQueryModel} from "../../model";
import {RootState} from "../store";
import { createGenericSlice, GenericState} from "../hooks";
import {getProductListAPI} from "../../api";

const initialState: GenericState<PageModel<ProductModel>> = {
    status: 'loading'
};


export const getProductList = createAsyncThunk<ResultModel<PageModel<ProductModel>>, PageQueryModel<ProductQueryModel>|undefined, ErrorModel>
(
    'product/list',
    async (queryVO, {rejectWithValue}) => {
        try {
            return await getProductListAPI(queryVO||{});
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
                return {status: "finished", data: action.payload.result};
            })
    }
})

export const selectProductReducer = (state: RootState) => state.product;
export const selectProductList = (state: RootState) => state.product.data;

// export const {} = productSlice.actions;
export default productSlice.reducer;
