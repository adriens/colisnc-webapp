import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {IToken, IUser} from './user.model';
import {JwtInterceptorService} from '../../core/services/jwt-interceptor.service';
import {AppState} from '../index';
import {select, Store} from '@ngrx/store';
import {sUser} from './user.selector';

@Injectable({ providedIn: 'root' })
export class UserService {
  token: IToken;

  constructor(private http: HttpClient, private jwt: JwtInterceptorService, private store: Store<AppState>) {
    //this.store.pipe(select(sUser)).subscribe(user =>  this.storeUser(user));
  }

  authenticate(user) {
    this.storeUser(user);
  }

  private storeUser(token: IToken) {
    this.token = token;
    if (token) {
      window.localStorage.setItem('rememberMe', JSON.stringify(token));
      this.jwt.setJwtToken(token.token);
    } else {
      window.localStorage.removeItem('rememberMe');
      this.jwt.removeJwtToken();
    }
  }
}
