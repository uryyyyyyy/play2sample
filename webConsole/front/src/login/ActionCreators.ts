import {MyAction, ActionTypes, JsonObject} from "./Models";
import * as axios from "axios";
import {browserHistory} from "react-router";

export function login(id: string, password: string) {
    return (dispatch: (action: MyAction) => any) => {

        function failCB(ex:Error):void {
            dispatch({ type: ActionTypes.LOGIN_FAIL, error: ex})
        }

        function successCB(json:Axios.AxiosXHR<any>):void {
            browserHistory.push('/authCheck');
        }

        dispatch({ type: ActionTypes.LOGIN_REQUEST});

        const config = {
            timeout: 5000,
            headers: {'Content-Type': 'application/json'}
        };

        const postObj = {id: id, password: password};
        return axios.post('/api/auth/login', JSON.stringify(postObj), config)
            .then(successCB)
            .catch(failCB)
    }
}