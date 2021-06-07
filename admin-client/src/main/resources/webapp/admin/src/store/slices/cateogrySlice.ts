import {
    createAsyncThunk
} from '@reduxjs/toolkit';
import ResultModel from "../../model/ResultModel";
import {ErrorModel} from "../../model/ErrorModel";
import {getHomeInfoAPI} from "../api/HomeAPI";
import {RootState} from "../store";
import { createGenericSlice, GenericState} from "../hooks";
import {CategoryModel} from "../../model/CategoryModel";
import {getCategoryListAPI} from "../api/CategoryAPI";

const initialState: GenericState<Array<CategoryModel>> = {
    status: 'loading'
};


export const getCategoryList = createAsyncThunk<ResultModel<Array<CategoryModel>>, {}, ErrorModel>
(
    'category/list',
    async ({}, {rejectWithValue}) => {
        try {
            return await getCategoryListAPI();
        } catch (errorResult) {
            return rejectWithValue(errorResult);
        }
    },
);

export const categorySlice = createGenericSlice({
    name: 'category',
    initialState: initialState, reducers: {
        error: (state) => {
            state.status = 'error';
            state.errorMsg = 'testing error';
        }
    },
    extraReducers: (builder) => {
        builder
            .addCase(getCategoryList.pending, (state, action) => {
                state.status = 'loading';
            })

            .addCase(getCategoryList.rejected, (state, action) => {
                state.status = 'error';
                state.errorMsg = action.payload?.msgDetail;
            })
            .addCase(getCategoryList.fulfilled, (state, action) => {

                return {status: "finished", data: action.payload.result};
            })
    }
})

export const selectCategoryReducer = (state: RootState) => state.category;

export const {error} = categorySlice.actions;
export default categorySlice.reducer;
