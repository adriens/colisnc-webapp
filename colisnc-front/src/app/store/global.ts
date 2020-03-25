import {HttpErrorResponse} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Actions, Effect, ofType, ROOT_EFFECTS_INIT} from '@ngrx/effects';
import {switchMap} from 'rxjs/operators';
import {EMPTY} from 'rxjs/internal/observable/empty';
import {Store} from '@ngrx/store';
import {AppState} from './index';
import {MatSnackBar} from '@angular/material';

export enum GlobalActionTypes {
  ERROR = '[error] !',
  INIT_STORE = '[Init] Store'
}

export class GlobalError {
  readonly type = GlobalActionTypes.ERROR;
  constructor(public error: HttpErrorResponse) { }
}

export class GlobalInit {
  readonly type = GlobalActionTypes.INIT_STORE;
  constructor() { }
}

@Injectable()
export class GlobalEffects {

  @Effect()
  refresh = this.actions.pipe(
    ofType(ROOT_EFFECTS_INIT), // lors du rechargement de la page ou après l'authentification
    switchMap(() => [new GlobalInit()])
  );

  /*@Effect()
  init = this.actions.pipe(
    ofType(GlobalActionTypes.INIT_STORE), // lors du rechargement de la page ou après l'authentification
    switchMap(() => [new LoadColis()])
  );*/

  @Effect()
  error = this.actions.pipe(
    ofType(GlobalActionTypes.ERROR),
    switchMap((action: GlobalError) => {
      this.snackbar.open(action.error.status === 504 ? 'Serveur momentanément indisponible' : action.error.error.message, 'Ok');
      return EMPTY;
    })
  );

  constructor(private snackbar: MatSnackBar, private actions: Actions, private store: Store<AppState>) { }

}
