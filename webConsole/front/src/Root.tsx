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
        <h1>React Redux sample</h1>
        <li><Link to="/" >Home</Link></li>
        <li><Link to={Paths.SAMPLE} >sample</Link></li>
        {this.props.children}
      </div>
    );
  }
}
