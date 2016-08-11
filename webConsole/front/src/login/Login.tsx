import * as React from "react";
import {LoginStore, DispatchActions} from "./Models";
import objectAssign = require("object-assign");

interface Props {
    state: LoginStore;
    actions: DispatchActions;
}

interface State {
    id: string;
    password: string;
}

export default class Login extends React.Component<Props, State> {

    state: State = {id: "", password: ""};

    changeId(e:any): void {
        this.setState(objectAssign({}, this.state, {id: e.target.value}));
    };

    changePassword(e:any): void {
        this.setState(objectAssign({}, this.state, {password: e.target.value}));
    };

    render() {
        return (
            <div>
                <p><input type="text" placeholder="name"
                          onChange={this.changeId.bind(this)}
                          value={this.state.id} />
                </p>
                <p><input type="password" placeholder="password"
                          onChange={this.changePassword.bind(this)}
                          value={this.state.password}/>
                </p>
                <button onClick={() => this.props.actions.login(this.state.id, this.state.password)}>login</button>
            </div>
        )
    }
}