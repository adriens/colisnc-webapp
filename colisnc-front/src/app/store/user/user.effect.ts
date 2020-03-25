import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Actions, Effect, ofType } from '@ngrx/effects';
import { map} from 'rxjs/operators';
import { ActionTypes, AuthenticateSuccess } from './user.actions';
import { UserService } from './user.service';
import { Store } from '@ngrx/store';
import { AppState } from '../index';


@Injectable()
export class UserModelEffects {

  constructor(private userService: UserService, private router: Router, private actions$: Actions, private store$: Store<AppState>) { }

  @Effect({dispatch: false})
  authenticateSuccess = this.actions$.pipe(
    ofType(ActionTypes.AUTHENTICATE_SUCCESS),
    map((action: AuthenticateSuccess) => {
      this.userService.authenticate(action.token);
    })
  );
}
