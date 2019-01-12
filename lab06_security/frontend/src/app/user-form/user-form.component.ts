import {Component, Input, OnInit, Output} from '@angular/core';
import {User} from '../api/user';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../user.service';
import {UserRoleService} from '../user-role.service';
import {UserRole} from '../api/userRole';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss']
})
export class UserFormComponent implements OnInit {

  user: User = {
    username: '',
    password: '',
    firstname: '',
    lastname: '',
    role:  {
      roleName: '',
    },
  };
  userRoles: Array<UserRole>;

  isCreateForm: boolean;

  selectedRole: UserRole;

  constructor(private userService: UserService, private userRoleService: UserRoleService,
              private route: ActivatedRoute, private router: Router) {
    this.isCreateForm = true;
  }

  ngOnInit() {
    // Get all User Roles
    this.userRoleService.getAll()
      .subscribe( (response: any) => {
        this.userRoles = response._embedded.roles;
      });

    // Handle 'update User'
    const username = this.route.snapshot.paramMap.get('username');
    if (username) {
      this.isCreateForm = false;

      this.userService.getByUsername(username)
        .subscribe((response: any) => {
          this.user = <User>response;
          // Do not show password on form
          // If password is empty on update no change will happen
          // If password is set, this one will encoded and set in backend
          this.user.password = '';
        });
    }
  }

  saveUser() {
    // Assign selected role to user
    this.user.role = this.selectedRole;

  if (this.user.id) {
    this.userService.update(this.user)
      .subscribe(() => {
        alert('updated successfully');
        this.router.navigate(['/user-list']);
      }, (error) => {
        alert('Sorry, unable to update due to server-errors!');
      });
  } else {
    this.userService.create(this.user)
      .subscribe(() => {
        alert('created successfully');
        this.router.navigate(['/user-list']);
      }, (error) => {
      alert('Sorry, unable to create new User due to server-errors!');
    });
  }

}


}
