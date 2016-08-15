import * as React from "react";
import {Link} from "react-router";
import {Paths} from "./Models";

interface Props {
    children: any
}

export default class Root extends React.Component<Props, {}> {

    render() {
        const props: any = {globalState: {num: 1}};
        const children: any = this.props.children;
        return (
            <div>
                <h1>play2 React</h1>
                <li><Link to="/" >Home</Link></li>
                <li><Link to={Paths.LOGIN} >Login</Link></li>
                {children && React.cloneElement(children, props)}
            </div>
        );
    }
}
