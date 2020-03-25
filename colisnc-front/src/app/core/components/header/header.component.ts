import {Component, HostListener, Input, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {Observable} from "rxjs";
import {AppState} from "../../../store/index";
import {Store} from "@ngrx/store";
import {sUser} from "../../../store/user/user.selector";
import {IUser} from "../../../store/user/user.model";
import {Logout} from "../../../store/user/user.actions";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  @Input() showFavoris: boolean;

  user: Observable<IUser>;

  showSocial: boolean;

  smallMode: boolean;

  @HostListener('window:resize')
  onWindowResize() {
    this.smallMode = window.screen.width < 400;
  }

  constructor(private store: Store<AppState>, private router: Router) { }

  ngOnInit() {
    this.smallMode = window.screen.width < 400;
    this.user = this.store.select(sUser);
  }

  goTo(id: string) {
    this.showFavoris = false;
    return this.router.navigate(['../search/', id]);
  }

  logout() {
    this.store.dispatch(new Logout());
  }


}
