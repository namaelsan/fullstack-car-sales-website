import { CanActivateFn } from '@angular/router';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';

export const adminGuard: CanActivateFn = () => {
  let authService = inject(AuthService);
  let router = inject(Router);

  const isAdmin = authService.isAdmin();

  if (isAdmin) {
    return true;
  }

  router.navigate(['login'])
  return false;
};
