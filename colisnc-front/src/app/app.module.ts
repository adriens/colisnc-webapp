import { CommonModule, registerLocaleData } from '@angular/common';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import localeFrExtra from '@angular/common/locales/extra/fr';
import localeFr from '@angular/common/locales/fr';
import { LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { EffectsModule } from '@ngrx/effects';
import { StoreModule } from '@ngrx/store';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { environment } from '../environments/environment';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CoreModule } from './core/module';
import { appEffects, getReducers, metaReducers, REDUCER_TOKEN } from './store';
import { FlexLayoutModule } from '@angular/flex-layout';
import {AgmCoreModule} from "@agm/core";
import {JwtInterceptorService} from "./core/services/jwt-interceptor.service";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    CommonModule,
    CoreModule,
    FlexLayoutModule,
    AppRoutingModule,
    StoreModule.forRoot(REDUCER_TOKEN, { metaReducers, runtimeChecks: environment.runtimeChecks }),
    EffectsModule.forRoot(appEffects),
    StoreDevtoolsModule.instrument({ name: '[COLISNC]', maxAge: 25, logOnly: environment.production }),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCytoaEdO3faTAGREnxEyLvMqHPxzVBhuM'
    }),
    HttpClientModule,

  ],
  providers: [
    { provide: LOCALE_ID, useValue: 'fr-FR' },
    { provide: HTTP_INTERCEPTORS, useExisting: JwtInterceptorService, multi: true },
    { provide: REDUCER_TOKEN, useFactory: getReducers }],
  bootstrap: [AppComponent]
})

export class AppModule {
}

registerLocaleData(localeFr, 'fr-FR', localeFrExtra);
