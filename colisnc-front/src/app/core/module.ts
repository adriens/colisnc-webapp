import {NgModule} from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {FromNowPipe} from './pipes/from-now.pipe';
import {RouterModule} from '@angular/router';

import {
  MatBadgeModule,
  MatButtonModule,
  MatCardModule,
  MatCheckboxModule,
  MatChipsModule,
  MatDatepickerModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule, MatMenuModule,
  MatNativeDateModule, MatPaginatorModule, MatProgressBarModule,
  MatProgressSpinnerModule,
  MatSelectModule,
  MatSnackBarModule,
  MatStepperModule,
  MatTableModule,
  MatTooltipModule
} from '@angular/material';
import {UnauthorizedComponent} from './components/unauthorized/unauthorized.component';
import {FlexLayoutModule} from '@angular/flex-layout';
import {DateService} from './services/date.service';
import {CdkColumnDef} from '@angular/cdk/table';
import {NotFoundComponent} from './components/not-found/not-found.component';
import {AuthenticationSocialService} from "./services/authentication.social.service";
import {JwtInterceptorService} from "./services/jwt-interceptor.service";
import {HeaderComponent} from "./components/header/header.component";
import {SocialComponent} from "./components/header/component/social/social.component";
import {FavorisComponent} from "./components/header/component/favoris/favoris.component";

const material = [
  MatSnackBarModule,
  MatCardModule,
  MatProgressSpinnerModule,
  MatTooltipModule,
  MatButtonModule,
  MatStepperModule,
  MatFormFieldModule,
  MatIconModule,
  MatChipsModule,
  MatInputModule,
  MatTableModule,
  MatDatepickerModule,
  MatNativeDateModule,
  MatSelectModule,
  MatCheckboxModule,
  MatBadgeModule,
  MatProgressBarModule,
  MatProgressSpinnerModule,
  MatPaginatorModule,
  MatMenuModule
];


@NgModule({
  declarations: [FromNowPipe, UnauthorizedComponent, NotFoundComponent, HeaderComponent, SocialComponent, FavorisComponent],
  imports: [CommonModule, FormsModule, ReactiveFormsModule, material, FlexLayoutModule],
  exports: [CommonModule, ReactiveFormsModule, FromNowPipe, RouterModule, material, FlexLayoutModule, SocialComponent, HeaderComponent, FavorisComponent],
  providers: [JwtInterceptorService, DateService, DatePipe, CdkColumnDef, AuthenticationSocialService]
})
export class CoreModule {}