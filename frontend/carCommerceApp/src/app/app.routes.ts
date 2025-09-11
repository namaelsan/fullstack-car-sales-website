import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: 'login',
        loadComponent: () => import('./login/login.component').then(m => m.LoginComponent),
        title: "Login"

    },
    {
        path: '',
        loadComponent: () => import('./main-page/main-page.component').then(m => m.MainPageComponent),
        title: "List"
    },
];
