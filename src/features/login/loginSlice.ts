import { createSlice, PayloadAction } from '@reduxjs/toolkit'

export interface LoginState {
    value: boolean
}

const initialState: LoginState = {
    value: !!(localStorage.getItem('token') && localStorage.getItem('loginFlag') === 'true'),
}

export const loginSlice = createSlice({
    name: 'loginFlag',
    initialState,
    reducers: {
        setLogin: (state, action: PayloadAction<boolean>) => {
            state.value = action.payload
            localStorage.setItem('loginFlag', `${action.payload}`)
        },
    },
})

export const { setLogin } = loginSlice.actions

export default loginSlice.reducer