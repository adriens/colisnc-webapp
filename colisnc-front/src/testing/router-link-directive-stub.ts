
import { HostListener, Input, Directive } from '@angular/core';
/**
 * @see https://angular.io/guide/testing#components-with-routerlink
 */
@Directive({
    // tslint:disable-next-line
    selector: '[routerLink]'
})
// tslint:disable-next-line
export class RouterLinkDirectiveStub {
    @Input('routerLink')
    linkParams: any;
    navigatedTo: any = null;
    @HostListener('click')
    onClick() {
        this.navigatedTo = this.linkParams;
    }
}
