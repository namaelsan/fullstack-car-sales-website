import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Role, User } from './user.models';
import { JWTToken } from './jwttoken.models';
import { Router } from '@angular/router';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = '/api'

  constructor(private http: HttpClient, private router: Router, private userService: UserService) { }

  login(user: User) {
    return this.http.post<JWTToken>(`${this.baseUrl}/login`, user).subscribe({
      next: (data) => {
          localStorage.setItem('JWT_TOKEN', data.token)
          this.getUserDataFromDB(data.id);
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
    localStorage.removeItem('username');
    localStorage.removeItem('user_roles');
    this.router.navigate(['']);
  }

  getUsername(): String {
    let name = localStorage.getItem('username');
    return name ? name : "";
  }

  getUserRoles(): Role[] {
    let rolesString = localStorage.getItem('user_roles');
    let roles: Role[] = JSON.parse(rolesString ? rolesString : '');
    return roles;
  }

  getUserId(): number {
    return Number(localStorage.getItem('user_roles'));
  }

  isAdmin(): boolean {
    return this.getUserRoles().some(role => role.roleName === "ADMIN");
  }

  getUserDataFromDB(id: number) {
    return this.userService.getUserById(id).subscribe({
      next: (user) => {
        localStorage.setItem('username', user.username!);
        localStorage.setItem('user_roles', JSON.stringify(user.roles));
      },
      error: (e) => {
        console.error("Error registering user: ", e)
      }
    })
  }

}
