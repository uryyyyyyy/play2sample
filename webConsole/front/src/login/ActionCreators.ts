import {MyAction, ActionTypes} from "./Models";
import * as axios from "axios";
import {browserHistory} from "react-router";
import {config} from "../Models";

export function login(id: string, password: string) {
    return (dispatch: (action: MyAction) => any) => {

        function failCB(ex:Error):void {
            dispatch({ type: ActionTypes.LOGIN_FAIL, error: ex})
        }

        function successCB(json:Axios.AxiosXHR<any>):void {
            browserHistory.push('/authCheck');
        }

        dispatch({ type: ActionTypes.LOGIN_REQUEST});

        const postObj = {id: id, password: password};
        return axios.post('/api/authentication/login', JSON.stringify(postObj), config)
            .then(successCB)
            .catch(failCB)
    }
}