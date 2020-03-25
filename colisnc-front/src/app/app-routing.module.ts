import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UnauthorizedComponent} from './core/components/unauthorized/unauthorized.component';
import {NotFoundComponent} from './core/components/not-found/not-found.component';

const routes: Routes = [
  { path: '', redirectTo: '/search', pathMatch: 'full'},
  { path: 'search', loadChildren: './views/search/search.module#SearchModule'},
  { path: '401', component: UnauthorizedComponent},
  { path: '404', component: NotFoundComponent},
  { path: '**', redirectTo: '404' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { onSameUrlNavigation: 'reload' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
