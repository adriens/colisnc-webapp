import { Injectable } from '@angular/core';
import * as Globals from '../../app.globals';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {IToken} from "../../store/user/user.model";
import {Store} from "@ngrx/store";
import {AppState} from "../../store";
import {AuthenticateSuccess} from "../../store/user/user.actions";



/**
 * Service d'authentification OpenId
 *
 * @author www.redstone.nc
 *
 */
@Injectable()
export class AuthenticationSocialService {

    // Store
    //private authStorage$: Observable<Auth>;

    // Constructeur
    constructor(private http: HttpClient, private store: Store<AppState>) {
        //this.authStorage$ = store.select<Auth>('authStorage');
    }

    // Verification si le client openId est pris en charge par l'application
    checkProvider(provider: string): void {
        let isProviderExists: boolean = false;
        Globals.providers.forEach((client) => {
            if (provider === client.name) {
                isProviderExists = true;
            }
        });
        if (!isProviderExists) {
            throw new Error('Le provider ' + provider + 'n est pas pris en charge');
        }

        console.log('provider = ' + provider);
    }

    // Methode de recuperation de l'url de connexion du provider openId
    // Le processus s'exécute en plusieurs étapes
    getURLProvider(provider: string): Observable<string> {

        //this.store.dispatch({ type: 'AUTHENTICATION_IN_PROGRESS' });

        return this.http.get('api/auth/openid/' + provider + '/authorization').pipe(
            map((response: any) => {
                let socialCodeUrl: string = response.url;
                if(socialCodeUrl) {
                    return socialCodeUrl;
                }
                //this.store.dispatch({ type: 'AUTHENTICATION_FAILURE' });
                return null;
            }));


    }

    // 3. On ouvre une nouvelle fenetre avec l'URL récupérée précédemment.
    // Cette fenetre est sur écoute pendant un temps limité
    // Si elle ne change pas d'état, elle se fermera automatiquement
    // Si l'utilisateur s'authentifie ou était deja authentifié, la fenetre se ferme et le code est récupéré dans l'URL
    getCode(href: string): string {
        if (href) {
            let re = /code=(.*)/;
            let found = href.match(re);

            if (found) {
                // Le parametre a été trouvé, on extrait les parametres de l url sous forme d un tableau clé/valeur
                // Cette fonction "parse" gère les différents encodages des navigateurs
                let parsed = this.parse(href.substr(Globals.oAuthCallbackUrl.length));

                // On récupere la valeur du code
                return parsed['code'];
            }
        }
        return null;
    }

    // 4. Envoi du code vers le serveur et attend le JWT en retour
    authenticate(provider: string, code:string): Observable<boolean> {
        // On envoi le code au serveur
        return this.http.post('api/auth/openid/token', { code: code, providerName: provider }).pipe(
            map((response: IToken) => {
                console.log(response);
                this.store.dispatch(new AuthenticateSuccess(response));
                let resultat = response;
                //let token = response.json() && response.json().token;
                if (resultat) {
                    // On récupere les informations en retour et on créé l'objet d'authentification
                    //let token = resultat.token;
                    //let username = resultat.username;

                    //let utilisateur: User = { username: username };
                    //let auth: Auth = { current: utilisateur, token: token, error: '', provider: provider };

                    // On change l'état de l'application et on authentifie l'utilisateur par la même occasion
                    //let payload = auth;
                    //this.store.dispatch({ type: 'USER_AUTHENTICATED', payload });
                    return true;
                }
                //this.store.dispatch({ type: 'AUTHENTICATION_FAILURE' });
                return false;
            }));
    }

    // Parse les paramètres d une url et les renvoie sous forme de tableau
    // Prend en compte les différents encodages des navigateurs
    // lifted from https://github.com/sindresorhus/query-string
    private parse(str) {
        if (typeof str !== 'string') {
            return {};
        }

        str = str.trim().replace(/^(\?|#|&)/, '');

        if (!str) {
            return {};
        }

        return str.split('&').reduce(function(ret, param) {
            let parts = param.replace(/\+/g, ' ').split('=');
            // Firefox (pre 40) decodes `%3D` to `=`
            // https://github.com/sindresorhus/query-string/pull/37
            let key = parts.shift();
            let val = parts.length > 0 ? parts.join('=') : undefined;

            key = decodeURIComponent(key);

            // missing `=` should be `null`:
            // http://w3.org/TR/2012/WD-url-20120524/#collect-url-parameters
            val = val === undefined ? null : decodeURIComponent(val);

            if (!ret.hasOwnProperty(key)) {
                ret[key] = val;
            } else if (Array.isArray(ret[key])) {
                ret[key].push(val);
            } else {
                ret[key] = [ret[key], val];
            }

            return ret;
        }, {});
    };
}
