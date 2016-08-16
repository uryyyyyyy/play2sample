import * as React from "react";
import * as ReactDOM from "react-dom";
import * as axios from "axios";

function loginNormal(){

  function successCB(val: any):void {
    location.href = "/";
  }

  axios.get("authentication/login/normal/pass2")
    .then(successCB)
    .catch(() => console.log("error"));
}

function loginAdmin(){

  function successCB(val: any):void {
    location.href = "/";
  }

  axios.get("authentication/login/admin/pass1")
    .then(successCB)
    .catch(() => console.log("error"));
}

function failCB():void {
  ReactDOM.render(
    <div>
      <button onClick={() => loginAdmin()}>login Admin</button>
      <button onClick={() => loginNormal()}>login Normal</button>
    </div>,
    document.getElementById('app')
  );
}

function successCB(val: any):void {
  alert("login済みです。ホームへ移動します。");
  location.href = "/";
}

axios.get("api/authorize/normal")
  .then(successCB)
  .catch(failCB);
