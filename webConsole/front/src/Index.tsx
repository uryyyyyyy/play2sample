import * as React from "react";
import * as ReactDOM from "react-dom";
import {Router, Route, browserHistory, IndexRoute} from "react-router";
import Root from "./Root";
import NotFound from "./NotFound";
import homeRoot from "./Home";
import {Provider} from "react-redux";
import store from "./Store";

ReactDOM.render(
    <Provider store={store}>
        <Router history={browserHistory}>
            <Route path='/' component={Root} >
                <IndexRoute component={homeRoot}/>
                <Route path="*" component={NotFound} />
            </Route>
        </Router>
    </Provider>,
    document.getElementById('app')
);
