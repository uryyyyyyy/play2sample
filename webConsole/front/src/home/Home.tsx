import * as React from "react";
import {HomeState, DispatchActions} from "./Models";

interface Props {
    state: HomeState;
    actions: DispatchActions;
}

export default class Home extends React.Component<Props, {}> {

    render() {
        return (
            <div>
                <button onClick={() => this.props.actions.checkAdminRole()}>checkAdminRole</button>
                <button onClick={() => this.props.actions.checkNormalRole()}>checkNormalRole</button>
                <button onClick={() => this.props.actions.logout()}>logout</button>
            </div>
        )
    }
}