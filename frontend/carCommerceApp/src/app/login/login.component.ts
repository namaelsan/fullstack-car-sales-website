import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { User } from '../user.models';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth.service';
import { TopBarComponent } from "../top-bar/top-bar.component";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, TopBarComponent],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user: User = {
    id: -1,
    password: "",
    roles: [],
    username: ""
  };

  constructor (private authService: AuthService) {}
  
  submitForm() {
    this.authService.login(this.user);
  }
}
