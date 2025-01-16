import { Component } from '@angular/core';
import {TestResponse} from "../../models/test-response";
import {TestService} from "../../services/test.service";
import {ProductCardComponent} from "../product-card/product-card.component";
import {NgForOf, NgIf} from "@angular/common";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [
    ProductCardComponent,
    NgIf,
    NgForOf,
    RouterLink
  ],
  templateUrl: './search.component.html',
  styleUrl: './search.component.scss'
})
export class SearchComponent {


  level: 'success' |'error' = 'success';
  message = '';
  show: boolean = false;
  content?: Array<TestResponse>;

  constructor(private testService: TestService) {}


  search(text: string) {
    console.log("======== " + text);

    this.testService.getProduct(text)
      .subscribe({
        next: (response) => {
          this.content = response;
          this.level = 'success';
          this.message = 'Get Product OK';
          this.show = true;
        },
        error: (err) => {
          console.log(err);
          this.level = 'error';
          this.message = err.error.error;
        }
      });
  }
}
