import {connect} from "react-redux";
import {DispatchActions} from "./Models";
import {Dispatch} from "redux";
import Home from "./Home";

export default connect(
    (store: any) => {return {state: store.authCheckReducer}},
    (dispatch: Dispatch<any>) => {return {actions: new DispatchActions(dispatch)}}
)(Home);
