import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { User } from "./user.models"
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = "http://localhost:10160/"

  constructor(private http: HttpClient) {  }
  
  registerUser(user: User): Observable<User>{
    return this.http.post<User>(this.baseUrl + "register", user);
  }

  loginUser(user: User): Observable<String>{
    return this.http.post<String>(this.baseUrl + "login", user);
  }

  getUserById(id: number): Observable<User>{
    return this.http.get<User>(`/api/user/${id}`);
  }
}
