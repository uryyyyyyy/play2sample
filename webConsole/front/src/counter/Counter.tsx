import * as React from "react";
import {getRequest} from "../utils/HttpClient";

export default class Counter extends React.Component<{}, {}> {

  normalAPI(){
    function failCB():void {}
    function successCB(val: any):void {
      alert("success")
    }
    getRequest<any>("api/authorize/normal", successCB, failCB);
  }

  adminAPI(){
    function failCB():void {}
    function successCB(val: any):void {
      alert("success")
    }
    getRequest<any>("api/authorize/admin", successCB, failCB);
  }

  logout(){
    function failCB():void {}
    function successCB(val: any):void {
      location.href = "/login";
    }
    getRequest<any>("authentication/logout", successCB, failCB);
  }

  render() {
    return (
      <div>
        <button onClick={() => this.adminAPI()}>Admin API</button>
        <button onClick={() => this.normalAPI()}>Normal API</button>
        <button onClick={() => this.logout()}>Logout</button>
      </div>
    )
  }
}