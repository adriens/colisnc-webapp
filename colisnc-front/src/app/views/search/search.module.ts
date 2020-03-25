import {NgModule} from '@angular/core';
import { AgmCoreModule, GoogleMapsAPIWrapper } from '@agm/core';
import {MatTableModule} from '@angular/material';
import {CommonModule} from '@angular/common';
import {SearchRoutingModule} from "./search.route.module";
import {SearchService} from "./search.service";
import {SearchComponent} from "./search.component";
import {CoreModule} from "../../core/module";
import {StepComponent} from "../step/step.component";

@NgModule({
  declarations: [SearchComponent, StepComponent],
  exports: [],
  imports: [CommonModule, SearchRoutingModule, AgmCoreModule, MatTableModule, CoreModule],
  providers: [SearchService, GoogleMapsAPIWrapper]
})
export class SearchModule {
}
