import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { JwtService } from 'src/app/jwt.service';
import { UserStaorageService } from 'src/app/user-staorage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

   loginForm! : FormGroup;
  hidePassword = true;
  

  

  constructor(
    private service: JwtService,
    private fb: FormBuilder,
    private router: Router,
    private snackbar: MatSnackBar
  ) { 
  }

 
  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', Validators.required, Validators.email],
      password: ['', Validators.required],
    })
  }

  togglePasswordVisibility(){
    this.hidePassword = !this.hidePassword;
  }
  submitForm() {

    this.service.login(this.loginForm.value).subscribe(
      (response) => {
        console.log(response);
        if (response.jwt != null) {
          alert("Hello, Your token is " + response.jwt);
          const jwtToken = response.jwt;
          localStorage.setItem('jwt', jwtToken);
          const formValues = this.loginForm.value;
          const email = formValues.email;
          const password = formValues.password;
           if(email == "admin@gmail.com" && password == "admin"){
            this.router.navigateByUrl('admin');
           }
           else {
             this.router.navigateByUrl('customer');
           }
         
        }
      },
      (error) => {
        this.snackbar.open('Bad credentials', 'ERROR', { duration: 5000 });
      }
    )
  }


}
