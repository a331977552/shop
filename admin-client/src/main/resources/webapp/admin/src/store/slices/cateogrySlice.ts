import {
    createAsyncThunk, createSlice, PayloadAction
} from '@reduxjs/toolkit';
import {RootState} from "../store";
import {GenericState} from "../hooks";
import {getCategoryListAPI,toggleVisibleAPI} from "../api/CategoryAPI";
import {CategoryModel, PageQueryModel, ErrorModel, ResultModel, PageModel, CategoryQueryVO} from "../../model";

interface CategoryState {
    categoryList: GenericState<PageModel<CategoryModel>>
    categoryUpdate: GenericState<CategoryModel>
}

const initialState: CategoryState = {
    categoryUpdate: {status: 'finished'},
    categoryList: {status: 'loading'}
};


export const getCategoryList = createAsyncThunk<ResultModel<PageModel<CategoryModel>>, PageQueryModel<CategoryQueryVO>, ErrorModel>(
    'category/list',
    async (pageQueryModel: PageQueryModel<CategoryQueryVO>, {rejectWithValue}) => {
        try {
            return await getCategoryListAPI(pageQueryModel);
        } catch (errorResult) {
            return rejectWithValue(errorResult);
        }
    },
);



export const toggleVisible = createAsyncThunk<ResultModel<string>,CategoryModel, ErrorModel>(
    'category/toggleVisible',
    async (categoryModel: CategoryModel, {rejectWithValue}) => {
        try {
            return await toggleVisibleAPI({...categoryModel,visible:!categoryModel.visible});
        } catch (errorResult) {
            return rejectWithValue(errorResult);
        }
    },
);

export const categorySlice = createSlice({
    name: 'cateogry',
    initialState: initialState,
    reducers: {
        deleteCategoryByID: (state, action: PayloadAction<CategoryModel>) => {
            let data = state.categoryList.data as PageModel<CategoryModel>;
            data.items = state.categoryList.data?.items.filter(item =>
                item.id !== action.payload.id) as CategoryModel[];
            const elementsWithSameParent = data.items.reduce((acc, curr) =>
                acc + curr.parent === action.payload.parent ? 1 : 0
            , 0)
            //sync parent leaf status
            if (elementsWithSameParent === 0) {
                let parent = data.items.find(item => item.id === action.payload.parent) as CategoryModel;
                parent.isleaf = true;
            }
        }
    },
    extraReducers: (builder => {
        builder
            .addCase(getCategoryList.pending, (state, action) => {
                state.categoryList.status = 'loading';
            })
            .addCase(getCategoryList.rejected, (state, action) => {
                state.categoryList.status = 'error';
                state.categoryList.errorMsg = action.payload?.msgDetail;
            })
            .addCase(getCategoryList.fulfilled, (state, action) => {
                state.categoryList = {status: "finished", data: action.payload.result};
            })
            .addCase(toggleVisible.pending, (state, action) => {
                state.categoryUpdate.status = 'loading';
            })
            .addCase(toggleVisible.rejected, (state, action) => {
                state.categoryUpdate.status = 'error';
                state.categoryUpdate.errorMsg = action.payload?.msgDetail;
            })
            .addCase(toggleVisible.fulfilled, (state, action) => {
                const categoryModel = action.meta.arg;
                const items = state.categoryList.data?.items as Array<CategoryModel>;
                let find = items.find(item=>item.id === categoryModel.id) as  CategoryModel;
                find.visible = !find.visible;
            })

    })
});
export const selectCategoryReducer = (state: RootState) => state.category;

export const {deleteCategoryByID} = categorySlice.actions;
export default categorySlice.reducer;
