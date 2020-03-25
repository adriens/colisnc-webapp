import {IColis} from './colis.model';

export enum ActionTypes {
  LOAD_COLIS = '[Colis] Load',
  COLIS_LOADED = '[Colis] Loaded',
  CLEAR = '[Colis] Clear'
}

export class LoadColis {
  readonly type = ActionTypes.LOAD_COLIS;
  constructor(public id: string) { }
}

export class ColisLoaded {
  readonly type = ActionTypes.COLIS_LOADED;
  constructor(public colis: IColis[]) { }
}

export class Clear {
  readonly type = ActionTypes.CLEAR;
}

export type ColisActions = LoadColis | ColisLoaded | Clear;
