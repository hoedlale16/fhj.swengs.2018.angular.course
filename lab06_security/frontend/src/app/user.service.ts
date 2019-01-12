import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {Router} from '@angular/router';
import {Subject} from 'rxjs';
import {JwtHelperService} from '@auth0/angular-jwt';
import {User} from './api/user';
import decode from 'jwt-decode';
import {UserRole} from './api/userRole';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  isLoggedIn: boolean;
  loggedInChange: Subject<boolean> = new Subject<boolean>();

  accessTokenLocalStorageKey = 'access_token';
  currentLoggedInUser: string;
  userRoles: Array<string>;

  constructor(private http: HttpClient, private router: Router) {
    this.isLoggedIn = !!localStorage.getItem(this.accessTokenLocalStorageKey);
    this.loggedInChange.subscribe((value) => {
      this.isLoggedIn = value;
    });
  }

  login(user) {
    return this.http.post('/api/auth/', user, {
      'headers': new HttpHeaders({'Content-Type': 'application/json'}),
      'responseType': 'text',
      observe: 'response'

      // Pipe map wird immer ausgeführt wenn login ausgeführt wird und damit der post getriggert wird.
      // Wird ausgeführt bevor der externe Caller sein ergebnis bkeommt.
    }).pipe(map((res: any) => {

      const jwt = res.headers.get('Authorization').replace(/^Bearer /,'');

      localStorage.setItem(this.accessTokenLocalStorageKey, jwt);

      this.currentLoggedInUser = user.username;

      // Try to geht roles from user - I have no fucking clue were the roles are stored and how I get these  from the fucking JWT
      const jwtHelper = new JwtHelperService();
      const decodedToken = jwtHelper.decodeToken(jwt);
      this.currentLoggedInUser = decodedToken.sub;
      this.userRoles = decodedToken.authorities;

      this.loggedInChange.next(true);
      console.log('hoedl7:');
      console.log(this.currentLoggedInUser);
      console.log(this.userRoles);

      // Navigate to actor-List
      this.router.navigate(['/actor-list']);
      return res;
    }));
  }

  logout() {
    localStorage.removeItem(this.accessTokenLocalStorageKey);
    this.loggedInChange.next(false);
    this.router.navigate(['/login']);
  }


  getByUsername(username: string) {
    return this.http.get('/api/users/' + username);
  }

  getRoleOfUser(username: string) {
    return this.http.get('/api/users/' + username + '/role');
  }

  getAll() {
    return this.http.get('/api/users/');
  }

  delete(user: User) {
    return this.http.delete('/api/users/' + user.username);
  }

  update(user: User) {
    return this.http.put('/api/users/' + user.username, user);
  }

  create(user: User) {
    return this.http.post('/api/users', user);
  }

}
