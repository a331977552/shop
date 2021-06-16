import {
    createAsyncThunk, createSlice, PayloadAction
} from '@reduxjs/toolkit';
import {RootState} from "../store";
import {GenericState} from "../hooks";
import {getCategoryListAPI, toggleCategoryVisibleAPI} from "../../api/CategoryAPI";
import {CategoryModel, PageQueryModel, ErrorModel, ResultModel, PageModel, CategoryQueryVO} from "../../model";
import {
    CategoryTree,
    convertToTreeCategory,
    convertToUITree,
    removeCategory, removeTreeNode, resetToLeafIfNecessary
} from "../../pages/category/CategoryConvertor";

interface CategoryState {
    categoryList: GenericState<PageModel<CategoryModel>>
    categoryUpdate: GenericState<CategoryModel>,
    uiTree?: CategoryTree[]
}

const initialState: CategoryState = {
    categoryUpdate: {status: 'finished'},
    categoryList: {status: 'loading'}
};

interface CategoryListAction {
    pageQuery?: PageQueryModel<CategoryQueryVO>,
}

export const getCategoryList = createAsyncThunk<ResultModel<PageModel<CategoryModel>>, CategoryListAction|undefined, ErrorModel>(
    'category/list',
    async (actions, {rejectWithValue}) => {
        try {
            return await getCategoryListAPI(actions?.pageQuery);
        } catch (errorResult) {
            return rejectWithValue(errorResult);
        }
    },
);


export const toggleVisible = createAsyncThunk<ResultModel<string>, CategoryModel, ErrorModel>(
    'category/toggleVisible',
    async (categoryModel: CategoryModel, {rejectWithValue}) => {
        try {
            return await toggleCategoryVisibleAPI(categoryModel);
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
            data.items = removeCategory(data.items, action.payload.id);
            resetToLeafIfNecessary(data.items, action.payload.parent);
            removeTreeNode(state.uiTree as CategoryTree[], action.payload.id + "");
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
                const items = state.categoryList.data?.items;
                (items || []).forEach(item => {
                    convertToTreeCategory(item, items || []);
                })
                state.uiTree = convertToUITree(items);
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
                let find = items.find(item => item.id === categoryModel.id) as CategoryModel;
                find.visible = categoryModel.visible;
                find.navVisible = categoryModel.navVisible;
            })
    })
});
export const selectCategoryReducer = (state: RootState) => state.category;
export const selectUITree = (state: RootState) => state.category.uiTree;
export const selectCategoryDataReducer = (state: RootState) => state.category.categoryList.data;

export const {deleteCategoryByID} = categorySlice.actions;
export default categorySlice.reducer;
