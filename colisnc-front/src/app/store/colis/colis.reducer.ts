import {IColisModelState} from './colis.model';
import {ActionTypes, ColisActions} from './colis.actions';
import {update} from "../redux";

const initialState: IColisModelState = {
  colis: [],
  loading: false
};

export function colisModelReducer(state: IColisModelState = initialState, action: ColisActions): IColisModelState {
  switch (action.type) {
    case ActionTypes.LOAD_COLIS : return {...state, loading: true, num: action.id};
    case ActionTypes.COLIS_LOADED : return {...state, loading: false, colis: action.colis};
    case ActionTypes.CLEAR: return {...state, colis: []};
    default: return state;
  }
}
