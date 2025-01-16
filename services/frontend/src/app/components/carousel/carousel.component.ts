import { Component } from '@angular/core';
import { MdbCarouselModule } from "mdb-angular-ui-kit/carousel";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-carousel',
  standalone: true,
  imports: [
    MdbCarouselModule,
    NgIf
  ],
  templateUrl: './carousel.component.html',
  styleUrl: './carousel.component.scss'
})
export class CarouselComponent {

  show: boolean = true;

}
