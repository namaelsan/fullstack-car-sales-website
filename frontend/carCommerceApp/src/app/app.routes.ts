import { Routes } from '@angular/router';
import { adminGuard } from './admin.guard';

export const routes: Routes = [
    {
        path: '',
        loadComponent: () => import('./main-page/main-page.component').then(m => m.MainPageComponent),
        title: "Sales"
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
    {
        path: 'admin/cars',
        loadComponent: () => import('./cars-page/cars-page.component').then(m => m.CarsPageComponent),
        title: "Edit",
        canActivate: [adminGuard]
    },
    {
        path: 'admin/brands',
        loadComponent: () => import('./brands-page/brands-page.component').then(m => m.BrandsPageComponent),
        title: "Edit",
        canActivate: [adminGuard]
    },
];
