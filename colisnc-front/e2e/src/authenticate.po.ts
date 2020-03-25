import { by, element } from 'protractor';

export class AuthenticatePage {
  get login() {
    return element(by.css('[placeholder="Utilisateur"]'));
  }
  get password() {
    return element(by.css('[placeholder="Mot de passe"]'));
  }
  get button() {
    return element(by.css('button'));
  }
}
