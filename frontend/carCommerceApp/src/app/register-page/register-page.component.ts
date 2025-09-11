import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TopBarComponent } from '../top-bar/top-bar.component';
import { AuthService } from '../auth.service';
import { User } from '../user.models';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-register-page',
  standalone: true,
  imports: [CommonModule, TopBarComponent, FormsModule],
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent {
  user: User = {
    password: "",
    roles: [],
    username: ""
  };

  constructor (private authService: AuthService) {}
  
  submitForm() {
    this.authService.register(this.user);
  }
}
