import {connect} from "react-redux";
import {DispatchActions} from "./Models";
import {Dispatch} from "redux";
import Login from "./Login";

export default connect(
    (store: any) => {return {state: store.loginReducer}},
    (dispatch: Dispatch<any>) => {return {actions: new DispatchActions(dispatch)}}
)(Login);
