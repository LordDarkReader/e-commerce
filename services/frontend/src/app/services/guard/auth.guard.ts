import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { KeycloakService } from "../keycloak/keycloak.service";

export const authGuard: CanActivateFn = () => {
  const tokenService   = inject(KeycloakService);
  const router = inject(Router);
  if (tokenService .keycloak.isTokenExpired()) {
    console.log('authGuard laslsdlsadlsa');
    router.navigate(['']);
    return false;
  }
  console.log('authGuard  true');
  return true;
};
