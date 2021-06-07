import {
    createAsyncThunk,
} from '@reduxjs/toolkit';
import ResultModel from "../../model/ResultModel";
import AuthenticationModel from "../../model/AuthenticationModel";
import {ErrorModel} from "../../model/ErrorModel";
import {RootState} from "../store";
import UserModel from '../../model/UserModel';
import {getUserInfoAPI, loginAPI} from "../api";
import {createGenericSlice, GenericState} from "../hooks";

const initialState: GenericState<UserModel> = {
    status: "loading"
};


export const login = createAsyncThunk<ResultModel<string>, AuthenticationModel, ErrorModel>
(
    'user/login',
    async (user: { username: string, password: string }, {rejectWithValue}) => {
        try {
            return await loginAPI(user);
        } catch (errorResult) {
            return rejectWithValue(errorResult);
        }
    },
);

export const getUserInfo = createAsyncThunk<ResultModel<UserModel>, {}, ErrorModel>
(
    'user/get',
    async ({}, {rejectWithValue}) => {
        try {
            return await getUserInfoAPI();
        } catch (errorResult) {
            return rejectWithValue(errorResult);
        }
    },
);


export const selectUser = (state: RootState) => state.user.data;
export const selectUserReducer = (state: RootState) => state.user;


export const userSlice = createGenericSlice(
    {
        name: 'user',
        initialState,
        reducers: {
            signOut: (state) => {
            }
        },
        extraReducers: (builder) => {
            builder
                .addCase(getUserInfo.pending, (state, action) => {
                    state.status = 'loading';
                })
                .addCase(getUserInfo.fulfilled, (state, action) => {
                    state.status = 'finished';
                    console.log(action.payload)
                    state.data = action.payload.result;
                })
                .addCase(getUserInfo.rejected, (state, action) => {
                    state.status = 'error';
                    state.errorMsg = action.payload?.msgDetail;
                })
        }
    }
);
export const {signOut} = userSlice.actions;
export default userSlice.reducer;
