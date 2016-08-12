import * as React from "react";
import {Link} from "react-router";
import {Paths} from "./Models";
const {ToastContainer} = require("react-toastr");

interface Props {
    children: any
}

export default class Root extends React.Component<Props, {}> {

    render() {
        const props: any = {globalState: {num: 1}};
        const children: any = this.props.children;
        return (
            <div>
                <h1>play2 Auth Sample</h1>
                <li><Link to="/" >Home</Link></li>
                <li><Link to={Paths.AUTH_CHECK} >AUTH_CHECK</Link></li>
                {children && React.cloneElement(children, props)}

                <p>by uryyyyyyy</p>
                <ToastContainer ref="container"
                                className="toast-top-right" />
            </div>
        );
    }
}
