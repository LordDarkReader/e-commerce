import {Component, Input} from '@angular/core';
import { TestResponse } from "../../models/test-response";

@Component({
  selector: 'app-product-card',
  standalone: true,
  imports: [],
  templateUrl: './product-card.component.html',
  styleUrl: './product-card.component.scss'
})
export class ProductCardComponent {

  private _test: TestResponse = {};


  get test(): TestResponse {
    return this._test;
  }

  @Input()
  set test(value: TestResponse) {
    this._test = value;
  }


  // @Output() private share: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();
  // @Output() private archive: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();
  // @Output() private addToWaitingList: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();
  // @Output() private borrow: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();
  // @Output() private edit: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();
  // @Output() private details: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();

  // onShare() {
  //   this.share.emit(this._test);
  // }
  //
  // onArchive() {
  //   this.archive.emit(this._test);
  // }
  //
  // onAddToWaitingList() {
  //   this.addToWaitingList.emit(this._test);
  // }
  //
  // onBorrow() {
  //   this.borrow.emit(this._test);
  // }
  //
  // onEdit() {
  //   this.edit.emit(this._test);
  // }
  //
  // onShowDetails() {
  //   this.details.emit(this._test);
  // }
}
