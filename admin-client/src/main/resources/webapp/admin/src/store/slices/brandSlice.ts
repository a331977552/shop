import {createAsyncThunk, createSlice, PayloadAction} from '@reduxjs/toolkit';
import {RootState} from "../store";
import {GenericState} from "../hooks";
import {BrandModel, BrandQueryModel, ErrorModel, PageModel, ResultModel,} from "../../model";

import {getBrandListAPI} from "../../api";


const initialState: GenericState<PageModel<BrandModel>> = {
    status: 'loading'
};


export const getBrandList = createAsyncThunk<ResultModel<PageModel<BrandModel>>, BrandQueryModel | undefined, ErrorModel>(
    'brand/list',
    async (queryModel, {rejectWithValue}) => {
        try {
            return await getBrandListAPI(queryModel || {});
        } catch (errorResult) {
            return rejectWithValue(errorResult);
        }
    },
);


export const brandSlice = createSlice({
    name: 'brand',
    initialState: initialState,
    reducers: {
        updateBrand: (state, action: PayloadAction<BrandModel>) => {
            const brandModel = action.payload;
            const items = state.data?.items as Array<BrandModel>;
            let find = items.find(item => item.id === brandModel.id) as BrandModel;
            find.visible = brandModel.visible;
        },
        deleteBrand: (state, action: PayloadAction<number>) => {
            const id = action.payload;
            const data = state.data as PageModel<BrandModel>
            const items=  data.items as Array<BrandModel>;
            data.items = items.filter(item => item.id !== id);
        }
    },
    extraReducers: (builder => {
        builder
            .addCase(getBrandList.pending, (state, action) => {
                state.status = 'loading';
            })
            .addCase(getBrandList.rejected, (state, action) => {
                state.status = 'error';
                state.errorMsg = action.payload?.msgDetail;
            })
            .addCase(getBrandList.fulfilled, (state, action) => {
                state.data = action.payload.result;
                state.status = 'finished';
            })
    })
});

export const selectBrandReducer = (state: RootState) => state.brand;
export const selectBrandList = (state: RootState) => state.brand.data?.items
export const {updateBrand,deleteBrand} = brandSlice.actions;
export default brandSlice.reducer;
