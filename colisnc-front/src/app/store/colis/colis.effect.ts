import {Injectable} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Actions, Effect, ofType} from '@ngrx/effects';
import {catchError, map, switchMap, withLatestFrom} from 'rxjs/operators';
import {of} from 'rxjs';
import {GlobalError} from '../global';
import {ColisService} from './colis.service';
import {ActionTypes, ColisLoaded, LoadColis} from './colis.actions';
import {select, Store} from "@ngrx/store";
import {AppState} from "../index";
import {sUser} from "../user/user.selector";
import {UpdateFavoris} from "../user/user.actions";
import {IColis} from "./colis.model";
import {IUser} from "../user/user.model";
import {SearchService} from "../../views/search/search.service";
import {sColisNum} from "./colis.selector";

@Injectable()
export class ColisModelEffects {

  @Effect()
  load = this.actions.pipe(
      ofType(ActionTypes.LOAD_COLIS),
      switchMap((action: LoadColis) => this.service.load(action.id)),
      map(colis => new ColisLoaded(colis))
  );

  @Effect()
  loaded = this.actions.pipe(
      ofType(ActionTypes.COLIS_LOADED),
      withLatestFrom(this.store.pipe(select(sUser))),
      withLatestFrom(this.store.pipe(select(sColisNum))),
      map(([[action, user], num]: [[ColisLoaded, IUser], string]) => {
        if (user && action.colis.length > 0) {
          let fav = Array.from(user.favoris);
          fav.push(num);
          return new UpdateFavoris({...user, favoris: fav});
        }
        return { type: 'NOT_EXIST' };
      })
  );

  constructor(private service: ColisService, private store: Store<AppState>, private actions: Actions) { }
}
