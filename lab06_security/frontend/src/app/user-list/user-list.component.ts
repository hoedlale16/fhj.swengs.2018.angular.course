import { Component, OnInit } from '@angular/core';
import {User} from '../api/user';
import {ActorService} from '../actor.service';
import {Router} from '@angular/router';
import {UserService} from '../user.service';
import {Actor} from '../api/actor';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {

  users: Array<User>;

  constructor(private userService: UserService, private router: Router ) { }

  ngOnInit() {
    this.userService.getAll().subscribe((response: any) => {
      this.users = response;
    });
  }

  deleteUser(user: User) {

    this.userService.delete(user)
      .subscribe(() => {
        this.ngOnInit();
      });

  }

  createUser() {
    this.router.navigate(['/user-form']);
  }

}
