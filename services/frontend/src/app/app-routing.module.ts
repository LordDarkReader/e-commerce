import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from "./components/home/home.component";
import { authGuard } from "./services/guard/auth.guard";
import { TestComponent } from "./components/test/test.component";
import {SearchComponent} from "./components/search/search.component";
import {AddProductComponent} from "./components/add-product/add-product.component";


const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'home',
    component: HomeComponent,
    // canActivate: [ authGuard ]
  },
  {
    path: 'test',
    component: TestComponent,
    canActivate: [ authGuard ]
  },
  {
    path: 'search',
    component: SearchComponent,
    canActivate: [ authGuard ]
  },
  {
    path: 'add-product',
    component: AddProductComponent,
    canActivate: [ authGuard ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
