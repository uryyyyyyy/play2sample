import {login} from "./ActionCreators";

export interface LoginStore {
}

export interface JsonObject {
    amount: number;
}

export interface MyAction {
    type: string;
    error?: Error;
}

export class DispatchActions {
    private dispatch: (action: any) => any;
    constructor(dispatch: (action: any) => any){
        this.dispatch = dispatch
    }

    public login(id: string, password: string) {
        this.dispatch(login(id, password))
    }
}

export class ActionTypes{
    static LOGIN_REQUEST = 'LOGOUT_REQUEST';
    static LOGOUT_REQUEST = 'LOGOUT_REQUEST';
    static LOGIN_SUCCESS = 'LOGIN_SUCCESS';
    static LOGIN_FAIL = 'LOGIN_FAIL';
}