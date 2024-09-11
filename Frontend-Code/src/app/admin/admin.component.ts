import { Component } from '@angular/core';
import { UserStaorageService } from '../user-staorage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {

  constructor(private router:Router){}

  logout() {
    UserStaorageService.signOut();
    this.router.navigateByUrl('login');
  }

}
