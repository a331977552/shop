import {
    AsyncThunk, CaseReducers,
    createAsyncThunk,
    createSlice, CreateSliceOptions,
    Slice, SliceCaseReducers,
    ValidateSliceCaseReducers
} from '@reduxjs/toolkit';


export default function build(name:string,initialState:any, reducers:any){
    return createSlice<typeof initialState,SliceCaseReducers<typeof initialState>, typeof name>({
        name:name,
        initialState:initialState,
        reducers:reducers
    });

}

