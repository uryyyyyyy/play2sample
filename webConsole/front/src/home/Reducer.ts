import {HomeState, MyAction, ActionTypes} from "./Models";
import objectAssign = require('object-assign');

const initialState:HomeState = {num: 0, loadingCount: 0};

export function authCheckReducer(state: HomeState = initialState, action: MyAction): HomeState {
    console.log(action); //check which action has occurred;
    //console.log(state);
    switch (action.type) {
        default:
            return state
    }
}