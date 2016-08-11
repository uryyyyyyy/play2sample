import {logout, checkNormalRole, checkAdminRole} from "./ActionCreators";

export interface HomeState {
}

export interface JsonObject {
    message: string;
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

    public checkAdminRole() {
        this.dispatch(checkAdminRole())
    }

    public checkNormalRole() {
        this.dispatch(checkNormalRole())
    }

    public logout() {
        this.dispatch(logout())
    }
}

export class ActionTypes{
    static REQUEST_START = 'LOGOUT_REQUEST';
    static REQUEST_SUCCESS = 'LOGIN_SUCCESS';
    static REQUEST_FAIL = 'LOGIN_FAIL';
}