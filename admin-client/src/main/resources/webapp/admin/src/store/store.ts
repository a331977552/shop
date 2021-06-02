import { configureStore, ThunkAction, Action } from '@reduxjs/toolkit';
import userSlice from './user/UserSlice';
import counterSlice from "../features/counter/counterSlice";

export const store = configureStore({
    reducer: {
        user: userSlice,
        counter:counterSlice
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
