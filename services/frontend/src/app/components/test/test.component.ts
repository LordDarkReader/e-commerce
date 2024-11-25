import { Component } from '@angular/core';
import { TestService } from "../../services/test.service";
import { NgForOf, NgIf } from "@angular/common";
import { TestResponse } from "../../models/test-response";
import { ProductCardComponent } from "../product-card/product-card.component";

@Component({
  selector: 'app-test',
  standalone: true,
  imports: [
    NgIf,
    ProductCardComponent,
    NgForOf
  ],
  templateUrl: './test.component.html',
  styleUrl: './test.component.scss'
})
export class TestComponent {

  level: 'success' |'error' = 'success';
  message = '';
  show: boolean = false;
  content?: Array<TestResponse>;

  constructor(private testService: TestService) {}

  test() {
    this.testService.getProducts()
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
