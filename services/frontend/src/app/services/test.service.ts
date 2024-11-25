import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TestService {

  constructor(private http: HttpClient) { }

  getProducts(): Observable<any> {
    const url = 'http://localhost:8222/api/v1/products';
    return this.http.get<any>(url);
  }
}
