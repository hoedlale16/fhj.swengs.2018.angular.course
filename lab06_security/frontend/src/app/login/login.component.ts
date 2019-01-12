import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {UserService} from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  user: any;

  constructor(private userService: UserService, private router: Router) {
  }

  ngOnInit() {
    this.user = {
      username: '',
      password: ''
    };
  }

  login() {
    // Send POST-Request with user and password and wailt until server response
    // UserService handles response and sotres access_tokes. The error case must handled here!
    this.userService.login(this.user).subscribe(() => {}, (error) => {
      alert('wrong username or password');
    });
  }
}
