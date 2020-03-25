export interface IColis {
  id: string;
  pays: string;
  localisation: string;
  typeEvenement: string;
  date: Date;
  lat: number;
  lng: number;
  locValid: boolean;
  icon: string;
  selected: boolean;
}

export interface IColisModelState {
  colis: IColis[];
  num?: string;
  loading: boolean;
}
