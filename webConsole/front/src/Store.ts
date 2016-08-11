import {loginReducer} from "./login/Reducer";
import {authCheckReducer} from "./home/Reducer";
import {createStore, applyMiddleware, combineReducers} from "redux";
import thunk from "redux-thunk";

export default createStore(
    combineReducers({
        loginReducer,
        authCheckReducer
    }),
    applyMiddleware(thunk)
);
