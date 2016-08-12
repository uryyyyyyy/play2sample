import {MyAction, ActionTypes} from "./Models";
import * as axios from "axios";
import {browserHistory} from "react-router";
import {config} from "../Models";

export function logout() {
    return (dispatch: (action: MyAction) => any) => {

        function failCB(ex:Error):void {
            dispatch({ type: ActionTypes.REQUEST_FAIL, error: ex})
        }

        function successCB(json:Axios.AxiosXHR<any>):void {
            browserHistory.push('/login');
        }

        dispatch({ type: ActionTypes.REQUEST_START});

        return axios.get('/api/authentication/logout', config)
            .then(successCB)
            .catch(failCB)
    }
}

export function checkAdminRole(toast: any) {
    return (dispatch: (action: MyAction) => any) => {

        function failCB(ex:any):void {
            console.log(ex);
            toast("error", "some error", "error");
            if(ex.status === 401){
                browserHistory.push('/login?failed=true');
            }
        }

        function successCB(json:Axios.AxiosXHR<any>):void {
            console.log(json);
            toast("info", json.data, "info");
        }

        dispatch({ type: ActionTypes.REQUEST_START});

        return axios.get('api/authorization/admin', config)
            .then(successCB)
            .catch(failCB)
    }
}

export function checkNormalRole(toast: any) {
    return (dispatch: (action: MyAction) => any) => {

        function failCB(ex:any):void {
            toast("error", "some error", "error");
            console.log(ex);
            if(ex.status === 401){
                browserHistory.push('/login?failed=true');
            }
        }

        function successCB(json:Axios.AxiosXHR<any>):void {
            console.log(json);
            toast("info", json.data, "info");
        }

        dispatch({ type: ActionTypes.REQUEST_START});

        return axios.get('api/authorization/normal', config)
            .then(successCB)
            .catch(failCB)
    }
}