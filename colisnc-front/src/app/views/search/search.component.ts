import {
  Component,
  OnInit,
  ViewChild,
  HostListener,
} from '@angular/core';
import {SearchService} from "./search.service";
import {IColis} from "../../store/colis/colis.model";
import {MatPaginator, MatTableDataSource} from '@angular/material';
import {sColis, sLoading, sMarker} from "../../store/colis/colis.selector";
import {AppState} from "../../store";
import {Store} from '@ngrx/store';
import {ActivatedRoute, NavigationEnd, NavigationStart, Router} from '@angular/router';
import {Clear, LoadColis} from "../../store/colis/colis.actions";
import {AgmMap} from '@agm/core';
import {IUser} from "../../store/user/user.model";
import {sUser} from "../../store/user/user.selector";
import {UpdateFavoris} from "../../store/user/user.actions";

@Component({
  selector: 'jhi-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {

  lat: number;
  lng: number;

  selected: string;

  loading: boolean = false;

  zoom: number = 1;

  colis: IColis[];
  markers: IColis[];

  user: IUser;

  showColis: boolean;

  showMap: boolean;

  @ViewChild('AgmMap', {static: true}) agmMap: AgmMap;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  @HostListener('window:resize')
  onWindowResize() {
    this.showMap = this.isLandscape() && window.screen.width >= 1024;
    this.agmMap.triggerResize();
  }

  dataSource: MatTableDataSource<IColis>;

  constructor(private store: Store<AppState>, private service: SearchService, private router: Router, private route: ActivatedRoute) {

    router.events.subscribe(e => {
      if (e instanceof NavigationEnd) {
        this.showColis = this.route.snapshot.params.id;
        this.store.dispatch(new Clear());
        this.store.dispatch(new LoadColis(this.route.snapshot.params.id));
      }
    });
  }

  ngOnInit() {

    this.showMap = this.isLandscape() && window.screen.width >= 1024;

    this.store.select(sUser).subscribe( user => this.user = user);

    this.store.select(sLoading).subscribe( loading => this.loading = loading);

    this.store.select(sColis).subscribe( colis => {
      this.colis = colis;
      this.dataSource = new MatTableDataSource(colis);
      this.dataSource.paginator = this.paginator;
    });

    this.store.select(sMarker).subscribe( markers => {
      this.markers = markers;
      //this.markers.forEach(m => m.icon = 'http://maps.google.com/mapfiles/ms/icons/red-dot.png');
    });
  }

  show(step: IColis) {
    if (step.locValid) {
      this.lat = step.lat;
      this.lng = step.lng;
      this.zoom = 13;
      this.markers.forEach(m => {
        m.icon = 'http://maps.google.com/mapfiles/ms/icons/red-dot.png';
        m.selected = false;
      });
      this.markers.filter(m => m.lat === step.lat && m.lng === step.lng).forEach(i => {
        i.icon = 'http://maps.google.com/mapfiles/ms/icons/green-dot.png';
        i.selected = i.id === step.id;
      });
    }

    this.selected = step.id;
  }

  search(value) {
    return this.router.navigate(['../search/', value]);
  }

  isLandscape() {
    return window.screen.width > window.screen.height;
  }
}
