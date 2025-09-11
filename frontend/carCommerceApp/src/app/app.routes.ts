import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: '',
        loadComponent: () => import('./main-page/main-page.component').then(m => m.MainPageComponent),
        title: "List"
    },
    {
        path: 'login',
        loadComponent: () => import('./login/login.component').then(m => m.LoginComponent),
        title: "Login"

    },
    {
        path: 'register',
        loadComponent: () => import('./register-page/register-page.component').then(m => m.RegisterPageComponent),
        title: "Register"
    },
];
