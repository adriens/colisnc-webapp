import {Injectable} from '@angular/core';

@Injectable({ providedIn: 'root' })
export class DateService {

  constructor() { }

  static i18nSelect(month: string): string {
    return month.substr(0, 1).replace(/(.?)/g, (match) => {
      if (match !== '') {
        return  match === 'a' || match === 'o' ? 'd\''.concat(month) : 'de '.concat(month);
      }
      return '';
    });
  }
}
