import {
    createAsyncThunk,
} from '@reduxjs/toolkit';
import {RootState} from "../store";
import {getUserInfoAPI, loginAPI} from "../../api";
import {createGenericSlice, GenericState} from "../hooks";
import {AuthenticationModel, ResultModel, UserModel,ErrorModel} from "../../model";

const initialState: GenericState<ResultModel<UserModel>> = {
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

export const getUserInfo = createAsyncThunk<ResultModel<UserModel>, null, ErrorModel>
(
    'user/get',
    async (foo, {rejectWithValue}) => {
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
                    state.data = action.payload;
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
