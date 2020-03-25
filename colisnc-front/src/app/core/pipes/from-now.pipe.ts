import {Pipe, PipeTransform} from '@angular/core';

import * as moment from 'moment';
import {Observable, timer} from 'rxjs';
import {distinctUntilChanged, map} from 'rxjs/operators';

/**ts
 * Affiche la durée depuis la date et est mis à jour automatiquement !
 */
@Pipe({
  name: 'fromNow'
})
export class FromNowPipe implements PipeTransform {

  transform(value: string): Observable<string> {
    return timer(0, 1000).pipe(
      map(() => value && moment(value).fromNow()),
      distinctUntilChanged()
    );
  }
}
