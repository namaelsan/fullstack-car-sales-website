import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './user.models';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = '/api'

  constructor(private http: HttpClient) { }

  login(user: User) {
    return this.http.post<string>(`${this.baseUrl}/login`, user).subscribe({
        next: (token) => {
          localStorage.setItem('JWT_TOKEN', token)
        },
        error: (e) => {
          console.error("Error updating car", e)
        }
    });
  }

  register(user: User) {
    return this.http.post<User>(`${this.baseUrl}/register`, user).subscribe({
        next: (user) => {
          localStorage.setItem('username', user.username!)
          localStorage.setItem('user_roles', JSON.stringify(user.roles))
          localStorage.setItem('user_id', user.id!.toString())
        },
        error: (e) => {
          console.error("Error updating car", e)
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
