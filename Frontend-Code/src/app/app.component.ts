import { Component } from '@angular/core';
import { UserStaorageService } from './user-staorage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'jwt-angular';

  isCustomerLoggedIn : boolean = UserStaorageService.isCustomerLoggedIn();
  isAdminLoggedIn : boolean = UserStaorageService.isAdminLoggedIn();

  constructor(private router: Router){ }

  ngOnInit(): void{
    this.router.events.subscribe(event => {
      this.isCustomerLoggedIn = UserStaorageService.isCustomerLoggedIn();
      this.isAdminLoggedIn = UserStaorageService.isAdminLoggedIn();
    })
  }

logout() {
  UserStaorageService.signOut();
  this.router.navigateByUrl('login');
}

}