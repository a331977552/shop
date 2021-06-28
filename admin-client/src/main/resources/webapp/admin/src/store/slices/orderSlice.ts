import {
    createAsyncThunk
} from '@reduxjs/toolkit';
import {
    ErrorModel,
    ResultModel,
    OrderModel,
    PageModel,
    PageQueryModel,
    OrderQueryModel
} from "../../model";
import {RootState} from "../store";
import { createGenericSlice, GenericState} from "../hooks";
import {getOrderListAPI} from "../../api/OrderAPI";

const initialState: GenericState<PageModel<OrderModel>> = {
    status: 'loading'
};


export const getOrderList = createAsyncThunk<ResultModel<PageModel<OrderModel>>, PageQueryModel<OrderQueryModel>|undefined, ErrorModel>
(
    'order/list',
    async (queryVO, {rejectWithValue}) => {
        try {
            return await getOrderListAPI(queryVO||{});
        } catch (errorResult) {
            return rejectWithValue(errorResult);
        }
    },
);


export const orderSlice = createGenericSlice({
    name: 'order',
    initialState: initialState, reducers: {
    },
    extraReducers: (builder) => {
        builder
            .addCase(getOrderList.pending, (state, action) => {
                state.status = 'loading';
            })

            .addCase(getOrderList.rejected, (state, action) => {
                state.status = 'error';
                state.errorMsg = action.payload?.msgDetail;
            })
            .addCase(getOrderList.fulfilled, (state, action) => {
                return {status: "finished", data: action.payload.result};
            })
    }
})

export const selectOrderReducer = (state: RootState) => state.order;
export const selectOrderList = (state: RootState) => state.order.data;

// export const {} = orderSlice.actions;
export default orderSlice.reducer;
