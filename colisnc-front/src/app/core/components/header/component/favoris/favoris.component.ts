import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {animate, state, style, transition, trigger} from "@angular/animations";
import {Store} from "@ngrx/store";
import {AppState} from "../../../../../store/index";
import {Router} from "@angular/router";
import {Observable} from "rxjs";
import {sFavoris} from "../../../../../store/user/user.selector";
import {LoadColis} from "../../../../../store/colis/colis.actions";

@Component({
  animations: [
    trigger('flyInOut', [
      state('in', style({ transform: 'translateY(0)' })),
      transition('void => *', [
        style({ transform: 'translateY(-100%)' }),
        animate(200)
      ])
    ])
  ],
  selector: 'app-favoris',
  templateUrl: './favoris.component.html',
  styleUrls: ['./favoris.component.css']
})
export class FavorisComponent implements OnInit {

  @Output() eventColis: EventEmitter<string> = new EventEmitter();

  favoris: Observable<string[]>;

  constructor(private store: Store<AppState>, private router: Router) {}

  ngOnInit() {
    this.favoris = this.store.select(sFavoris);
  }

  click(id: string) {
    this.eventColis.emit(id);
    //this.store.dispatch(new LoadColis(id));

    //return this.router.navigate(['../search/', id], { skipLocationChange: true });
  }
}
