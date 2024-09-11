import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerComponent } from './customer.component';
import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';



const routes: Routes = [{ path: '', component: CustomerComponent },
                        {path: 'customer/dashboard', component:UserDashboardComponent}
                        
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
