import {IUserModelState} from './user.model';
import {ActionTypes, UserActions} from './user.actions';

const initialState: IUserModelState = {
  loading: false,
};

export function userModelReducer(state: IUserModelState = initialState, action: UserActions): IUserModelState {
  switch (action.type) {
    case ActionTypes.AUTHENTICATE_INIT: return {...state, loading: true};
    case ActionTypes.AUTHENTICATE_SUCCESS: return {...state, loading: false, user: action.token.user, token: action.token.token, authentificationFailure: null};
    case ActionTypes.AUTHENTICATE_FAIL: return {...state, loading: false, authentificationFailure: action.error};
    case ActionTypes.UPDATE_FAVORIS: return {...state, user: action.user};
    case ActionTypes.PASSWORD_UPDATED: return {...state, loading: false, user: action.user};
    case ActionTypes.LOGOUT : return {...state, user: undefined};
    default:
      return state;
  }
}




