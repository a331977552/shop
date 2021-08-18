import {createAsyncThunk, createSlice, PayloadAction} from '@reduxjs/toolkit';
import {RootState} from "../store";
import {GenericState} from "../hooks";
import {DeliveryCompanyModel, DeliveryCompanyQueryModel, ErrorModel, PageModel, ResultModel,} from "../../model";

import {getDeliveryCompanyListAPI} from "../../api";


const initialState: GenericState<PageModel<DeliveryCompanyModel>> = {
    status: 'loading'
};


export const getDeliveryCompanyList = createAsyncThunk<ResultModel<PageModel<DeliveryCompanyModel>>, DeliveryCompanyQueryModel | undefined, ErrorModel>(
    'delivery_company/list',
    async (queryModel, {rejectWithValue}) => {
        try {
            return await getDeliveryCompanyListAPI(queryModel);
        } catch (errorResult) {
            return rejectWithValue(errorResult);
        }
    },
);


export const deliveryCompanySlice = createSlice({
    name: 'delivery_company',
    initialState: initialState,
    reducers: {

    },
    extraReducers: (builder => {
        builder
            .addCase(getDeliveryCompanyList.pending, (state, action) => {
                state.status = 'loading';
            })
            .addCase(getDeliveryCompanyList.rejected, (state, action) => {
                state.status = 'error';
                state.errorMsg = action.payload?.msgDetail;
            })
            .addCase(getDeliveryCompanyList.fulfilled, (state, action) => {
                state.data = action.payload.result;
                state.status = 'finished';
            })
    })
});


export const selectDeliveryCompanyReducer = (state: RootState) => state.deliveryCompany;
export const selectDeliveryCompanyList = (state: RootState) => state.deliveryCompany.data?.items;
export default deliveryCompanySlice.reducer;
