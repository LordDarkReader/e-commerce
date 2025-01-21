import {Component, OnInit} from '@angular/core';
import {TestService} from "../../services/test.service";
import {AddProductRequest} from "../../models/add-product-request";
import {TestResponse} from "../../models/test-response";
import {Observable} from "rxjs";
import {HttpEventType, HttpResponse} from "@angular/common/http";
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";
import {ProductDescRequest} from "../../models/product-desc-request";

@Component({
  selector: 'app-add-product',
  standalone: true,
  imports: [
    NgIf,
    AsyncPipe,
    NgForOf
  ],
  templateUrl: './add-product.component.html',
  styleUrl: './add-product.component.scss'
})
export class AddProductComponent implements OnInit {

  selectedFile: File;
  retrievedImage: any;
  base64Data: any;
  retrieveResponse: any;
  message: string;
  imageName: any;
  uploadImageData: FormData;


  selectedFiles: FileList;
  currentFile: File;
  progress = 0;

  fileInfos: Observable<any>;

  private addProductRequest: AddProductRequest = {};
  private addProductDescRequest: ProductDescRequest = {};

  constructor(private testService: TestService) {
  }

  public onFileChanged(event) {
    //Select File
    this.selectedFile = event.target.files[0];
  }

  ngOnInit() {
    //this.fileInfos = this.testService.getFiles();
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }


  onUpload() {
    console.log(this.selectedFile);
    this.uploadImageData = new FormData();
    this.uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);


    console.log('dasdsadsadasd ' + this.selectedFile.name);
    //console.log('dasdsadsadasd ' + uploadImageData.get(0).);

    //this.addProductRequest.image = Object.assign(this.addProductRequest.image, { image: uploadImageData });
    //this.addProductRequest.image = Object.assign(this.addProductRequest.image, { image: uploadImageData });

    this.addProductRequest.image = this.uploadImageData;
   // this.addProductRequest.image = {uploadImageData};

    this.addProductRequest.description = 'description1';
    this.addProductRequest.price = 23;
   // this.addProductRequest.availableQuantity = 28.0;
    this.addProductRequest.categoryId = 1;
    this.addProductRequest.name = 'dasdasdasds';
    this.addProductRequest.id = 111;

    this.testService.addProduct(this.addProductRequest);
  }


  upload() {
    this.progress = 0;

    this.currentFile = this.selectedFiles.item(0);
    this.testService.upload(this.currentFile).subscribe(
      event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progress = Math.round(100 * event.loaded / event.total);
        } else if (event instanceof HttpResponse) {
          this.message = event.body.message;
          this.fileInfos = this.testService.getFiles();
        }
      },
      err => {
        this.progress = 0;
        this.message = 'Could not upload the file!';
        this.currentFile = undefined;
      });

    this.selectedFiles = undefined;
  }

  upload2() {
    this.progress = 0;

    this.addProductDescRequest.description = 'description1';
    //this.addProductDescRequest.price = 29;
    //this.addProductDescRequest.categoryId = 1;
    this.addProductDescRequest.filename = 'name1';

    this.currentFile = this.selectedFiles.item(0);

    this.testService.upload2(this.currentFile, this.addProductDescRequest).subscribe();


    this.selectedFiles = undefined;
  }


  // getImage() {
  //   //Make a call to Spring Boot to get the Image Bytes.
  //   this.httpClient.get('http://localhost:8080/image/get/' + this.imageName)
  //     .subscribe(
  //       res => {
  //         this.retrieveResponse = res;
  //         this.base64Data = this.retrieveResponse.picByte;
  //         this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
  //       }
  //     );
  // }

}
