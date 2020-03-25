import { convertToParamMap, ParamMap, Params } from '@angular/router';
import { ReplaySubject } from 'rxjs';

/**
 * An ActivateRouteSnapshot test with a `paramMap`.
 *
 * @see https://angular.io/guide/testing#activatedroutestub
 */
export class ActivatedRouteSnapshotStub {
    constructor(initialParams?: Params, initialQueryParams?: Params, ) {
        this.paramMap = convertToParamMap(initialParams);
        this.queryParamMap = convertToParamMap(initialQueryParams);
    }

    readonly paramMap: ParamMap;

    readonly queryParamMap: ParamMap;
}
