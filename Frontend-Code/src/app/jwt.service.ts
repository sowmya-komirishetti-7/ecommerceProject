import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { UserStaorageService } from './user-staorage.service';

const BASE_URL = ["http://localhost:8080/"]

@Injectable({
  providedIn: 'root'
})
export class JwtService {


  constructor(private http: HttpClient, private userStorageService:UserStaorageService) { }

  register(signRequest: any): Observable<any> {
    return this.http.post(BASE_URL + 'user/signup', signRequest);
  }

  login(loginRequest: any): Observable<any> {
    return this.http.post(BASE_URL + 'user/authenticate', loginRequest)
  }

  // login(username: string, password: string): any{
  //   const headers = new HttpHeaders().set('content-Type', 'application/json');
  //   const body = {username, password};

  //   return this.http.post(BASE_URL + 'user/authenticate', body, {headers, observe: 'response'}).pipe(
  //     map((res) => {
  //       const token = res.headers.get('authentication')?.substring(7);
  //       const user = res.body;
  //       if(token && user){
  //         this.userStorageService.saveToken(token);
  //         this.userStorageService.saveUser(user);
  //         return true;
  //       }
  //       return false;
  //     })
  //   )
  // }
  hello(): Observable<any> {
    return this.http.get(BASE_URL + 'api/hello', {
        // headers: this.createAuhtorizationHeader()
    })
  }

  private createAuhtorizationHeader() {
    const jwtToken = localStorage.getItem('jwt');
    if (jwtToken) {
      console.log("JWT token found in local storage", jwtToken);
      return new HttpHeaders().set(
        "Authorization", "Bearer " + jwtToken
      )
    } else {
      console.log("JWT token not found in local storage");
    }
    return null;
  }


}
