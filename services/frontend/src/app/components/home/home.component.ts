import { Component } from '@angular/core';
import { TestService } from "../../services/test.service";
import {NgForOf} from "@angular/common";
import {CarouselComponent} from "../carousel/carousel.component";
import {SearchComponent} from "../search/search.component";


@Component({
  selector: 'app-home',
  standalone: true,
    imports: [
        NgForOf,
        CarouselComponent,
        SearchComponent
    ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
}
