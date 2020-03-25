import {AppState} from '../index';
import {createSelector} from '@ngrx/store';

export const sState = (state: AppState) => state;

export const sColisModel = createSelector(sState, state => state.colisModel);

export const sColisNum = createSelector(sColisModel, model => model.num);

export const sColis = createSelector(sColisModel, model => model.colis);

export const sLoading = createSelector(sColisModel, model => model.loading);

export const sMarker = createSelector(sColis, colis => colis.filter(c => c.locValid));
