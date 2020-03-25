import { RouterModule, Routes } from '@angular/router';
import {NgModule} from '@angular/core';
import {SearchComponent} from "./search.component";

const searchRoutes: Routes = [
  { path: '', component: SearchComponent},
  { path: ':id', component: SearchComponent}
];

@NgModule({
  imports: [RouterModule.forChild(searchRoutes)],
  exports: [RouterModule]
})
export class SearchRoutingModule {}
