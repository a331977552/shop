import { configureStore, ThunkAction, Action } from '@reduxjs/toolkit';
import userReducer from './slices/userSlice';
import homeReducer from "./slices/homeSlice";
import productListReducer from "./slices/productSlice";
import categoryReducer from "./slices/cateogrySlice";

export const store = configureStore({
    reducer: {
        user: userReducer,
        home:homeReducer,
        product:productListReducer,
        category:categoryReducer
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
