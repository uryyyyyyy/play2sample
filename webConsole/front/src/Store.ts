import {loginReducer} from "./login/Reducer";
import {authCheckReducer} from "./authCheck/Reducer";
import {createStore, applyMiddleware, combineReducers} from "redux";
import thunk from "redux-thunk";

export default createStore(
    combineReducers({
        loginReducer,
        authCheckReducer
    }),
    applyMiddleware(thunk)
);
