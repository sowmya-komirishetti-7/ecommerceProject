import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AdminService } from '../../service/admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-paso-category',
  templateUrl: './paso-category.component.html',
  styleUrls: ['./paso-category.component.css']
})
export class PasoCategoryComponent {

  categoryForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackbar:MatSnackBar,
    private adminService: AdminService

  ){}

  ngOnInit(): void{
    this.categoryForm = this.fb.group({
      name: [null, [Validators.required]],
      description: [null, [Validators.required]],
    })
  }

  addCategory(): void{
    if(this.categoryForm.valid){
      this.adminService.addCategory(this.categoryForm.value).subscribe((res) =>{
        if(res.id != null){
          this.snackbar.open('Category Posted Successfully', 'close', {
            duration: 5000
          } );
          this.router.navigateByUrl('/admin/dashboard');
        }else{
          this.snackbar.open(res.message, 'close', {
            duration: 5000,
            panelClass: 'error-snackbar'
          });
        }
    })
  }else {
    this.categoryForm.markAllAsTouched();
  }
    }
    
    }
   
  
