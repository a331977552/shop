import {
    createAsyncThunk, createSlice, PayloadAction
} from '@reduxjs/toolkit';
import {
    ErrorModel,
    ResultModel,
    PageQueryModel,
    ProductSpecQueryModel,
    PageModel,
    ProductSpecModel
} from "../../model";
import {RootState} from "../store";
import {GenericState} from "../hooks";
import {getProductSpecListAPI} from "../../api/ProductSpecAPI";

const initialState: GenericState<PageModel<ProductSpecModel>> = {
    status: 'loading'
};


export const getProductSpecList = createAsyncThunk<ResultModel<PageModel<ProductSpecModel>>, PageQueryModel<ProductSpecQueryModel>, ErrorModel>
(
    'product/spec/list',
    async (queryModel, {rejectWithValue}) => {
        try {
            return await getProductSpecListAPI(queryModel);
        } catch (errorResult) {
            return rejectWithValue(errorResult);
        }
    },
);

export const productSpecSlice = createSlice({
    name: 'productSpec',
    initialState: initialState,
    reducers: {
        deleteProductSpecByIdLocally: (state, action: PayloadAction<ProductSpecModel>) => {
            let data = state.data as PageModel<ProductSpecModel>;
            data.items = data.items.filter(item =>
                item.id !== action.payload.id) as ProductSpecModel[];
        }
    },
    extraReducers: (builder) => {
        builder
            .addCase(getProductSpecList.pending, (state, action) => {
                state.status = 'loading';
            })

            .addCase(getProductSpecList.rejected, (state, action) => {
                state.status = 'error';
                state.errorMsg = action.payload?.msgDetail;
            })
            .addCase(getProductSpecList.fulfilled, (state, action) => {
                return {status: "finished", data: action.payload.result};
            })
    }
})

export const selectProductSpecReducer = (state: RootState) => state.productSpec;
export const {deleteProductSpecByIdLocally} = productSpecSlice.actions
export default productSpecSlice.reducer;
