import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './user.models';
import { JWTToken } from './jwttoken.models';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = '/api'

  constructor(private http: HttpClient, private router: Router) { }

  login(user: User) {
    return this.http.post<JWTToken>(`${this.baseUrl}/login`, user).subscribe({
        next: (data) => {
          localStorage.setItem('JWT_TOKEN', data.token)
          this.router.navigate(['']);
        },
        error: (e) => {
          console.error("Error logging in: ", e)
        }
    });
  }

  register(user: User) {
    return this.http.post<User>(`${this.baseUrl}/register`, user).subscribe({
        next: (user) => {
          localStorage.setItem('username', user.username!)
          localStorage.setItem('user_roles', JSON.stringify(user.roles))
          localStorage.setItem('user_id', user.id!.toString())
          this.router.navigate(['login']);

        },
        error: (e) => {
          console.error("Error registering user: ", e)
        }
    });
  }

  getToken() {
    return localStorage.getItem('JWT_TOKEN');
  }

  isLoggedIn() {
    return this.getToken() !== null;
  }

  logOut() {
    localStorage.removeItem('JWT_TOKEN');
    this.router.navigate(['']);
  }

  getUsername(): String {
    let name = localStorage.getItem('user_roles');
    return name ? name:"";
  }

  getUserRoles(): String[] {
    let rolesString = localStorage.getItem('user_roles');
    let roles: String[] = JSON.parse(rolesString? rolesString: '');
    return roles;
  }

  getUserId(): number {
    return Number(localStorage.getItem('user_roles'));
  }

}
