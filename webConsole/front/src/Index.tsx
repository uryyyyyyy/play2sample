import * as React from "react";
import * as ReactDOM from "react-dom";
import {Router, Route, browserHistory} from "react-router";
import Root from "./Root";
import NotFound from "./NotFound";
import authCheckRoot from "./authCheck/Root";
import loginRoot from "./login/Root";
import {Provider} from "react-redux";
import store from "./Store";
import {Paths} from "./Models";

ReactDOM.render(
    <Provider store={store}>
        <Router history={browserHistory}>
            <Route path='/' component={Root} >
                <Route path={Paths.LOGIN} component={loginRoot} />
                <Route path={Paths.AUTH_CHECK} component={authCheckRoot} />
                <Route path="*" component={NotFound} />
            </Route>
        </Router>
    </Provider>,
    document.getElementById('app')
);
