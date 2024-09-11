import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStaorageService } from 'src/app/user-staorage.service';

const BASIC_URL = "http://localhost:8080/";
@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http:HttpClient) { }

  addCategory(categoryDto:any): Observable<any>{
    return this.http.post(BASIC_URL +'user/category', categoryDto);
    // , {
    //   headers: this.createAuthorizationHeader(),
    // })
  }

  getAllCategories(): Observable<any>{
    return this.http.get(BASIC_URL +'user/categories');
    
  }

  addProduct(productDto:any): Observable<any>{
    return this.http.post(BASIC_URL + 'user/product', productDto);
  }

  private createAuthorizationHeader(): HttpHeaders{
    return new HttpHeaders().set(
      "Authorization", "Bearer "+ UserStaorageService.getToken()
    )
  }
}
