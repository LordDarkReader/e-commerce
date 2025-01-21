import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpParams, HttpRequest} from "@angular/common/http";
import {catchError, Observable, tap, throwError} from "rxjs";
import { AddProductRequest } from "../models/add-product-request";
import {ProductDescRequest} from "../models/product-desc-request";

@Injectable({
  providedIn: 'root'
})
export class TestService {

  private baseUrl = 'http://localhost:8222/api/v1/products';

  constructor(private http: HttpClient) { }

  getProducts(): Observable<any> {
    const url = 'http://localhost:8222/api/v1/products';
    return this.http.get<any>(url);
  }

  getProduct(text: string): Observable<any> {
    const url = `http://localhost:8222/api/v1/products/search`;
    let queryParams = new HttpParams().append("text", text)
    return this.http.get<any>(url, {params: queryParams}
    );
  }

  addProduct(request: AddProductRequest) {
    const url = `http://localhost:8222/api/v1/products/add-product`;
    this.http.post(url, request, {observe: 'response'})
      .subscribe((response) => {
        if (response.status === 200) {
          console.log('Image uploaded successfully') ;
        } else {
          console.log('Image not uploaded successfully');
        }
      });
  }


  upload(file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();

    formData.append('file', file);

    const req = new HttpRequest('POST', `${this.baseUrl}/new/upload`, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(req);
  }

  upload2(file: File, desc: ProductDescRequest): Observable<any> {
    const formData: FormData = new FormData();

    formData.append('file', file);

    formData.append('desc', JSON.stringify(desc));

    return this.http.post(`${this.baseUrl}/new/upload-desc`, formData, { observe: 'response' })
      .pipe(
        tap((response) => {
          if (response.status === 200) {
            console.log('Image uploaded successfully');
          } else {
            console.log('Image not uploaded successfully');
          }
        }),
        catchError((error) => {
          console.error('Error uploading image:', error);
          return throwError(error);
        })
      );
  }

  getFiles(): Observable<any> {
    return this.http.get(`${this.baseUrl}/files`);
  }
}
