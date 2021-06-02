import { TypedUseSelectorHook, useDispatch, useSelector } from 'react-redux';
import {AppDispatch, RootState} from "./store";

// Use throughout your app instead of plain `useDispatch` and `useSelector`
export const useAppDispatch = () => useDispatch<AppDispatch>(); //force conversion to AppDispatch -> store.dispatch
export const useAppSelector: TypedUseSelectorHook<RootState> = useSelector;//force conversion,强转成 store.getState
