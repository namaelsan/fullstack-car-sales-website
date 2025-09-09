import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: 'login',
        loadComponent: () => import('./login/login.component').then(m => m.LoginComponent),
        title: "Login"

    },
    {
        path: '',
        loadComponent: () => import('./listing-page/listing-page.component').then(m => m.ListingPageComponent),
        title: "List"
    },
];
