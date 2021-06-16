import {
    createAsyncThunk, createSlice, PayloadAction
} from '@reduxjs/toolkit';
import {
    ErrorModel,
    ResultModel,
    PageQueryModel,
    PageModel,
     ProductAttrModel, ProductAttrQueryModel
} from "../../model";
import {RootState} from "../store";
import {GenericState} from "../hooks";
import {getProductAttrListAPI} from "../../api/ProductAttrAPI";

const initialState: GenericState<PageModel<ProductAttrModel>> = {
    status: 'loading'
};


export const getProductAttrList = createAsyncThunk<ResultModel<PageModel<ProductAttrModel>>, PageQueryModel<ProductAttrQueryModel>, ErrorModel>
(
    'product/attr/list',
    async (queryModel, {rejectWithValue}) => {
        try {
            return await getProductAttrListAPI(queryModel);
        } catch (errorResult) {
            return rejectWithValue(errorResult);
        }
    },
);

export const productAttrSlice = createSlice({
    name: 'productAttr',
    initialState: initialState,
    reducers: {
        deleteProductAttrByIdLocally: (state, action: PayloadAction<ProductAttrModel>) => {
            let data = state.data as PageModel<ProductAttrModel>;
            data.items = data.items.filter(item =>
                item.id !== action.payload.id) as ProductAttrModel[];
        }
    },
    extraReducers: (builder) => {
        builder
            .addCase(getProductAttrList.pending, (state, action) => {
                state.status = 'loading';
            })

            .addCase(getProductAttrList.rejected, (state, action) => {
                state.status = 'error';
                state.errorMsg = action.payload?.msgDetail;
            })
            .addCase(getProductAttrList.fulfilled, (state, action) => {
                return {status: "finished", data: action.payload.result};
            })
    }
})

export const selectProductAttrReducer = (state: RootState) => state.productAttr;
export const {deleteProductAttrByIdLocally} = productAttrSlice.actions
export default productAttrSlice.reducer;
