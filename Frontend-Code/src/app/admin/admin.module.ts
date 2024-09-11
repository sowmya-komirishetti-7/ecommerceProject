import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AngularMaterialModule } from '../angular-material/angular-material.module';
import { PasoCategoryComponent } from './components/post-category/paso-category.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CategoryComponent } from './components/category/category.component';
import { PostProductComponent } from './components/post-product/post-product.component';



@NgModule({
  declarations: [
    AdminComponent,
    DashboardComponent,
    PasoCategoryComponent,
    CategoryComponent,
    PostProductComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    AngularMaterialModule,
    ReactiveFormsModule
   
  ]
})
export class AdminModule { }
