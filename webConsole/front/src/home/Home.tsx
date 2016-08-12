import * as React from "react";
import {HomeState, DispatchActions} from "./Models";
const ReactToastr = require("react-toastr");
const {ToastContainer} = ReactToastr;

interface Props {
    state: HomeState;
    actions: DispatchActions;
}

export default class Home extends React.Component<Props, {}> {

    showToast(){
        const container:any = this.refs['container'];
        container.error(
            "some error occurred",
            "Alert!", {
                timeOut: 30000,
                progressBar: true,
                closeButton: true
            });
    }

    render() {
        return (
            <div>
                <ToastContainer ref="container"
                                className="toast-top-right" />
                <button onClick={this.showToast.bind(this)}>checkAdminRole</button>
                <button onClick={() => this.props.actions.checkAdminRole()}>checkAdminRole</button>
                <button onClick={() => this.props.actions.checkNormalRole()}>checkNormalRole</button>
                <button onClick={() => this.props.actions.logout()}>logout</button>
            </div>
        )
    }
}