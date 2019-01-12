import { Component, OnInit } from '@angular/core';
import {UserService} from '../user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-menu-management',
  templateUrl: './menu-management.component.html',
  styleUrls: ['./menu-management.component.scss']
})
export class MenuManagementComponent implements OnInit {

  isLoggedIn: boolean;

  constructor(private userService: UserService, private router: Router) {
  }

  ngOnInit() {
    this.isLoggedIn = this.userService.isLoggedIn;
    this.userService.loggedInChange.subscribe((isLoggedIn) => {
      this.isLoggedIn = isLoggedIn;
    });
  }

  logout() {
    this.userService.logout();
  }

  showActorManagement() {
    this.router.navigate( ['/actor-list/']);
  }

  showUserManagement() {
    this.router.navigate( ['/user-list/']);
  }

}
