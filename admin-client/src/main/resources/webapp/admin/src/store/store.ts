import { configureStore, ThunkAction, Action } from '@reduxjs/toolkit';
import userSlice from './slices/UserSlice';
import homeSlice from "./slices/HomeSlice";

export const store = configureStore({
    reducer: {
        userReducer: userSlice,
        homeReducer:homeSlice,
    },
});

export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>;

export type AppThunk<ReturnType = void> = ThunkAction<
    ReturnType,
    RootState,
    unknown,
    Action<string>
    >;
