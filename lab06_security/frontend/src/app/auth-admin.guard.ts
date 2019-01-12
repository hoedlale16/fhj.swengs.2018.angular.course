import { Injectable } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import { Observable } from 'rxjs';
import {UserService} from './user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthAdminGuard implements CanActivate {

  constructor(private userService: UserService, private router: Router) {
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
      if (this.userService.isLoggedIn && this.userService.userRoles.includes('ROLE_ADMIN')) {
        return true;
      } else {
        this.router.navigate(['/actor-list']);
        return false;
      }
  }
}
