import {LoginStore, MyAction, ActionTypes} from "./Models";
import objectAssign = require('object-assign');

const initialState:LoginStore = {};

export function loginReducer(state: LoginStore = initialState, action: MyAction): LoginStore {
    console.log(action.type); //check which action has occurred;
    //console.log(state);
    switch (action.type) {
        default:
            return state
    }
}