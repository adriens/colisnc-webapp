export interface IToken {
  token: string;
  user: IUser;
}

export interface IUser {
  name: string;
  favoris: string[];
  picture: string;

}

export interface IUserModelState {
  token?: string;
  user?: IUser;
  loading: boolean;
  authentificationFailure?: any;
}
