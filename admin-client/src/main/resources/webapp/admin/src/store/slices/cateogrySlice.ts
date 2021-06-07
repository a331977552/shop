import {
    createAsyncThunk
} from '@reduxjs/toolkit';
import {RootState} from "../store";
import { createGenericSlice, GenericState} from "../hooks";
import {getCategoryListAPI} from "../api/CategoryAPI";
import {CategoryModel, PageQueryModel, ErrorModel, ResultModel, PageModel} from "../../model";

const initialState: GenericState<PageModel<CategoryModel>> = {
    status: 'loading'
};


export const getCategoryList = createAsyncThunk<ResultModel<PageModel<CategoryModel>>, PageQueryModel<any>, ErrorModel>(
    'category/list',
    async (pageQueryModel: PageQueryModel<any>, {rejectWithValue}) => {
        try {
            return await getCategoryListAPI(pageQueryModel);
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
                console.log(action.payload)
                return {status: "finished", data: action.payload.result};
            })
    }
})

export const selectCategoryReducer = (state: RootState) => state.category;

export const {error} = categorySlice.actions;
export default categorySlice.reducer;
