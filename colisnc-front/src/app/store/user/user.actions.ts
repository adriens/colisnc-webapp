import {IToken, IUser} from './user.model';

export enum ActionTypes {
  AUTHENTICATE_INIT = '[User] Init Authenticate',
  AUTHENTICATE_SUCCESS = '[User] Success Authenticate',
  AUTHENTICATE_FAIL = '[User] Fail Authenticate',
  AUTHENTICATE_CHECK = '[User] Check Password',
  UPDATE_FAVORIS = '[User] Update Favoris',
  PASSWORD_UPDATED = '[User] Password Updated',
  LOGOUT = '[User] Logout',
}

export class Authenticate {
  readonly type = ActionTypes.AUTHENTICATE_INIT;
  constructor(public login: string, public password: string) { }
}

export class AuthenticateSuccess {
  readonly type = ActionTypes.AUTHENTICATE_SUCCESS;
  constructor(public token: IToken) { }
}

export class AuthenticateCheck {
  readonly type = ActionTypes.AUTHENTICATE_CHECK;
  constructor(public userId: string) { }
}

export class AuthenticateFail {
  readonly type = ActionTypes.AUTHENTICATE_FAIL;
  constructor(public error: any) { }
}

export class UpdateFavoris {
  readonly type = ActionTypes.UPDATE_FAVORIS;
  constructor(public user: IUser) { }
}

export class PasswordUpdated {
  readonly type = ActionTypes.PASSWORD_UPDATED;
  constructor(public user: IUser) { }
}

export class Logout {
  readonly type = ActionTypes.LOGOUT;
}

export type UserActions = Authenticate
  | AuthenticateSuccess
  | AuthenticateFail
  | UpdateFavoris
  | PasswordUpdated
  | Logout;
