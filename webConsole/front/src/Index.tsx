import * as React from "react";
import * as ReactDOM from "react-dom";
import {Router, Route, browserHistory, IndexRoute} from "react-router";
import Root from "./Root";
import NotFound from "./NotFound";
import Home from "./Home";
import Login from "./Login";
import {Provider} from "react-redux";
import store from "./Store";
import {Paths} from "./Models";

ReactDOM.render(
  <Provider store={store}>
    <Router history={browserHistory}>
      <Route path='/' component={Root} >
        <IndexRoute component={Home}/>
        <Route path={Paths.LOGIN} component={Login} />
        <Route path="*" component={NotFound} />
      </Route>
    </Router>
  </Provider>,
  document.getElementById('app')
);
