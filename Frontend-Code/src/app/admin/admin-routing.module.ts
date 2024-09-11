import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { PasoCategoryComponent } from './components/post-category/paso-category.component';
import { CategoryComponent } from './components/category/category.component';


const routes: Routes = [{ path: '', component: AdminComponent },
                        { path:'dashboard', component: DashboardComponent},
                        
                        { path: 'category', component: CategoryComponent},
                        
                       
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
