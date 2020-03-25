import {ActionReducer, ActionReducerMap, MetaReducer} from '@ngrx/store';
import { InjectionToken } from '@angular/core';
import {colisModelReducer} from "./colis/colis.reducer";
import {IColisModelState} from "./colis/colis.model";
import {ColisModelEffects} from "./colis/colis.effect";
import {GlobalEffects} from "./global";
import {UserModelEffects} from "./user/user.effect";
import {IUserModelState} from "./user/user.model";
import {userModelReducer} from "./user/user.reducer";
import {ActionTypes} from "./user/user.actions";
import {localStorageSync} from "ngrx-store-localstorage";

const reducers = {
  colisModel: colisModelReducer,
  userModel: userModelReducer
}

export interface AppState {
  colisModel: IColisModelState;
  userModel: IUserModelState;
}

export function getReducers() {
  return reducers;
}

export function clearState(reducer) {
  return (state: AppState, action): AppState => {
    if (action.type === ActionTypes.LOGOUT) {
      state = undefined;
    }
    return reducer(state, action);
  };
}

export function localStorageSyncReducer(reducer: ActionReducer<any>): ActionReducer<any> {
  return localStorageSync({keys: ['userModel'], rehydrate: true})(reducer);
}

export const metaReducers: Array<MetaReducer<any, any>> = [clearState, localStorageSyncReducer];

export const REDUCER_TOKEN = new InjectionToken<ActionReducerMap<AppState>>('Registered Reducers');

export const appEffects = [ColisModelEffects, UserModelEffects, GlobalEffects];
