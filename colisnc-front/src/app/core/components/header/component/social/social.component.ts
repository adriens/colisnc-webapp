import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MatIconRegistry} from "@angular/material";
import {DomSanitizer} from "@angular/platform-browser";
import {animate, state, style, transition, trigger} from "@angular/animations";
import {Store} from "@ngrx/store";
import {AppState} from "../../../../../store/index";
import {Router} from "@angular/router";
import {AuthenticationSocialService} from "../../../../services/authentication.social.service";
import {interval, Observable} from "rxjs";
import {sFavoris} from "../../../../../store/user/user.selector";
import {take} from "rxjs/operators";

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
  selector: 'app-social',
  templateUrl: './social.component.html',
  styleUrls: ['./social.component.css']
})
export class SocialComponent implements OnInit {

  @Output() eventClose: EventEmitter<any> = new EventEmitter();

  favoris: Observable<string[]>;

  // Gestionnaire de fenêtre
  windowHandle: any = null;
  // URL de connexion vers le client OpenId distant
  socialCodeUrl: string = '';

  // Interval entre chaque point d'écoute en ms
  intervalLength = 100;

  // Nombre de points d'écoute de la fenêtre d'authentification
  nbLoops = 600;

  // L'abonnement à la fenetre d'authentification
  subscription: any = null;

  // Le code fourni par le client OpenId distant
  private code: string = '';

  constructor(private matIconRegistry: MatIconRegistry, private domSanitizer: DomSanitizer, private store: Store<AppState>, private router: Router, private authenticationSocialService: AuthenticationSocialService) {
    this.matIconRegistry.addSvgIcon(`google`, this.domSanitizer.bypassSecurityTrustResourceUrl('../../assets/icons/search.svg'));
  }

  ngOnInit() {
    this.favoris = this.store.select(sFavoris);
  }

  // Authentification par OpenId
  social(provider: string) {
    if (this.windowHandle == null || this.windowHandle.closed) {
      // 1. On vérifie si le client openId est pris en charge par l'application
      //this.authenticationSocialService.checkProvider(provider);

      this.eventClose.emit(true);

      // 2. On demande au serveur l'URL de connexion pour ouvrir le fenetre d'authentification du client OpenId
      //this.loading = true;
      this.authenticationSocialService.getURLProvider(provider).subscribe(
          (resultat) => {
            if(resultat) {
              this.socialCodeUrl = resultat;
            } else {
              throw new Error('Impossible de communiquer avec le fournisseur distant.');
            }
          },
          (err) => console.log(err),
          () => this.askForCode(provider)
      );
    }
  }

  // 3. On ouvre une nouvelle fenetre avec l'URL récupérée précédemment.
  // Cette fenetre est sur écoute pendant un temps limité
  // Si elle ne change pas d'état, elle se fermera automatiquement
  // Si l'utilisateur s'authentifie ou était deja authentifié, la fenetre se ferme et le code est récupéré dans l'URL
  private askForCode(provider: string): void {



    // Ouverture de la fenetre
    let options = `width=700,height=700,left=0,top=0`;
    this.windowHandle = window.open(this.socialCodeUrl, provider + ' Login', options);

    // Timer d'écoute
    let listener$ = interval(this.intervalLength).pipe(take(this.nbLoops));

    // On s'abonne au timer
    this.subscription = listener$.subscribe(
        (resultat) => {
          try {
            // Si le gestionnaire de fenetre est à l'état "closed", c'est qu'il a fermé la fenetre manuellement: On arrete tout !
            if (this.windowHandle.closed) {
              this.subscription.unsubscribe();
            } else {

              // On verifie si la fenetre a changée d'état en vérifiant si le parametre "code=" est présent dans l'url de la fenetre
              try {
                this.code = this.authenticationSocialService.getCode(this.windowHandle.location.href);
              } catch (e) {
                // TODO: try catch à retirer, tout doit être en https
              }
              if (this.code) {
                // On force l'abonnement a changé d'état
                this.subscription.complete();
              }
            }

          } catch (e) {
            // TODO: try catch à retirer, tout doit être en https
            if (!(e.stack).startsWith('Error: Blocked a frame with origin')) {
              throw e;
            }
          }
        },
        (err) => {
          //this.store.dispatch({ type: 'AUTHENTICATION_FAILURE' });
          this.windowHandle.close();
          //this.manageErrors(err);
        },
        () => {
          // On ferme la fenetre et on passe a l'authentification
          this.windowHandle.close();
          this.authenticate(provider);
        }
    );
  }

  // 4. Envoi du code vers le serveur et attend le JWT en retour
  authenticate(provider: string): void {


    this.authenticationSocialService.authenticate(provider, this.code).subscribe(
        (resultat) => {
          if(resultat === false) {
            throw new Error('L\'authentification n\'a pas pu aboutire.');
          }
        },
        (err) => console.log(err),
        () => {
          // Redirection une fois authentifié
          this.router.navigate(['/']);
        }
    );
  }



}
