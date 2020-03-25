import {AppState} from '../index';
import {createSelector} from '@ngrx/store';

export const sState = (state: AppState) => state;

export const sUserModel = createSelector(sState, state => state.userModel);

export const sUserLoading = createSelector(sUserModel, model => model.loading);

export const sFavoris = createSelector(sUserModel, model => {
  if (model && model.user && model.user.favoris) {
    return model.user.favoris.filter(onlyUnique);
  }
});

export const sUser = createSelector(sUserModel, model => model && model.user);

function onlyUnique(value, index, self) {
  return self.indexOf(value) === index;
}