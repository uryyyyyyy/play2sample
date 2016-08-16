import {connect} from "react-redux";
import {Dispatch} from "redux";
import Counter from "./Counter";

export default connect(
  (store: any) => {return {}},
  (dispatch: Dispatch<any>) => {return {}}
)(Counter);
