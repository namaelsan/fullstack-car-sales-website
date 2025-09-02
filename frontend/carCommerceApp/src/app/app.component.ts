import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { ListingPageComponent } from './listing-page/listing-page.component';
import { TopBarComponent } from './top-bar/top-bar.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [ListingPageComponent, TopBarComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'carCommerceApp';
}
