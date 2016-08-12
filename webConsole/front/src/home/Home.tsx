import * as React from "react";
import {HomeState, DispatchActions} from "./Models";

interface Props {
    state: HomeState;
    globalState: any;
    actions: DispatchActions;
}

export default class Home extends React.Component<Props, {}> {

    render() {
        console.log(this.props.globalState);
        return (
            <div>
                <button onClick={() => this.props.globalState.showToast("title", "msg", "error")}>show toast</button>
                <button onClick={() => this.props.actions.checkAdminRole(this.props.globalState.showToast)}>checkAdminRole</button>
                <button onClick={() => this.props.actions.checkNormalRole(this.props.globalState.showToast)}>checkNormalRole</button>
                <button onClick={() => this.props.actions.logout()}>logout</button>
            </div>
        )
    }
}