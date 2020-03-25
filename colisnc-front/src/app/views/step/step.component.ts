import {Component, Input, OnInit} from '@angular/core';
import {IColis} from "../../store/colis/colis.model";

@Component({
  selector: 'app-step',
  templateUrl: './step.component.html',
  styleUrls: ['./step.component.scss']
})
export class StepComponent implements OnInit {

  @Input() step: IColis;
  @Input() selected: string;

  constructor() { }

  ngOnInit() {}

  getIcon() {

    switch (this.step.typeEvenement) {
      case 'Votre courrier/colis a été livré' :
        return 'done';
      case 'Votre courrier/colis n\'a pas pu être livré' :
        return 'clear';
      case 'Votre courrier/colis est en cours d\'acheminement.' :
        return 'flight';
      case 'Votre courrier/colis est arrivé dans le pays de destination' :
        return 'flight_land';
      case 'Votre courrier/colis a été pris en charge' :
        return 'local_post_office';
      case 'Votre courrier/colis est en cours de dédouanement' :
        return 'attach_money';
      case 'Votre courrier/colis a quitté le pays d\'origine' :
        return 'flight_takeoff';
    }
  }
}
