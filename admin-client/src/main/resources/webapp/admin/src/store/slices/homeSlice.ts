import {
    createAsyncThunk
} from '@reduxjs/toolkit';
import ResultModel from "../../model/ResultModel";
import {ErrorModel} from "../../model/ErrorModel";
import HomeModel from "../../model/HomeModel";
import {getHomeInfoAPI} from "../api/HomeAPI";
import {RootState} from "../store";
import { createGenericSlice, GenericState} from "../hooks";

const initialState: GenericState<HomeModel> = {
    status: 'loading'
};


export const getHomeInfo = createAsyncThunk<ResultModel<HomeModel>, {}, ErrorModel>
(
    'home/getInfo',
    async ({}, {rejectWithValue}) => {
        try {
            return await getHomeInfoAPI();
        } catch (errorResult) {
            return rejectWithValue(errorResult);
        }
    },
);

export const homeSlice = createGenericSlice({
    name: 'home',
    initialState: initialState, reducers: {
        error: (state) => {
            state.status = 'error';
            state.errorMsg = 'testing error';
        }
    },
    extraReducers: (builder) => {
        builder
            .addCase(getHomeInfo.pending, (state, action) => {
                state.status = 'loading';
            })

            .addCase(getHomeInfo.rejected, (state, action) => {
                state.status = 'error';
                state.errorMsg = action.payload?.msgDetail;
            })
            .addCase(getHomeInfo.fulfilled, (state, action) => {

                return {status: "finished", data: action.payload.result};
            })
    }
})

export const selectHomeReducer = (state: RootState) => state.home;

export const {error} = homeSlice.actions;
export default homeSlice.reducer;
