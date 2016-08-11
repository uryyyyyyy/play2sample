import * as React from "react";
import {Link} from "react-router";
import {Paths} from "./Models";

interface Props {
    children: any
}

export default class Root extends React.Component<Props, {}> {
    render() {
        return (
            <div>
                <h1>play2 Auth Sample</h1>
                <li><Link to="/" >Home</Link></li>
                <li><Link to={Paths.AUTH_CHECK} >AUTH_CHECK</Link></li>
                {this.props.children}

                <p>by uryyyyyyy</p>
            </div>
        );
    }
}
