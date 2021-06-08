import { TypedUseSelectorHook, useDispatch, useSelector } from 'react-redux';
import {AppDispatch, RootState} from "./store";
import {
    ActionReducerMapBuilder,
    CaseReducers,
    createSlice,
    PayloadAction,
    SliceCaseReducers,
    ValidateSliceCaseReducers
} from "@reduxjs/toolkit";

// Use throughout your app instead of plain `useDispatch` and `useSelector`
export const useAppDispatch = () => useDispatch<AppDispatch>(); //force conversion to AppDispatch -> store.dispatch
export const useAppSelector: TypedUseSelectorHook<RootState> = useSelector;//force conversion,强转成 store.getState



export interface GenericState<T> {
    data?: T,
    errorMsg?:string,
    status: 'loading' | 'finished' | 'error'
}

declare type NoInfer<T> = [T][T extends any ? 0 : never];

export const createGenericSlice = <T, Reducers extends SliceCaseReducers<GenericState<T>>>({
          name = '',
          initialState,
          reducers,extraReducers= {}}: {
    name: string
    initialState: GenericState<T>
    reducers: ValidateSliceCaseReducers<GenericState<T>, Reducers>
    extraReducers?: CaseReducers<NoInfer<GenericState<T>>, any> | ((builder: ActionReducerMapBuilder<NoInfer<GenericState<T>>>) => void);
}) => {

    return createSlice({
        name,
        initialState,
        reducers: {
            //
            // /**
            //  * If you want to write to values of the state that depend on the generic
            //  * (in this case: `state.data`, which is T), you might need to specify the
            //  * State type manually here, as it defaults to `Draft<GenericState<T>>`,
            //  * which can sometimes be problematic with yet-unresolved generics.
            //  * This is a general problem when working with immer's Draft type and generics.
            //  */
            // success(state: GenericState<T>, action: PayloadAction<T>) {
            //     state.data = action.payload
            //     state.status = 'finished'
            // },
            ...reducers,
        },
        extraReducers:extraReducers
    })
}

