import { Component } from '@angular/core';
import { TestService } from "../../services/test.service";
import {NgForOf} from "@angular/common";


@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
}
