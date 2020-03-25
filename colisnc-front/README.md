# RsCraFront

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 7.3.0.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

Une version se basant sur des données mockés se lance avec la commande `yarn start:mock`.
Cette commande lance à la fois:
- le serveur de mock [`@ng-apimock`](https://github.com/ng-apimock/core) qui se base sur les fichiers du répertoire `e2e/mock/*.mock.json` _(dans lequel chaque changement est automatiquement pris en compte)_
- le `ng serve` avec le fichier de proxy ̀`src/proxy-mock.conf.json` qui regirige les appels vers le serveur de mock

Ces données mockés servent essentiellement aux test _end-to-end_ mais peuvent s'avverer pratiques durant le developpement car il est plus aisé de générer des cas de figures.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

NOTE: Les données sont mockés (voir `e2e/mocks`), donc pas besoin de lancer le back au préalable

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).
