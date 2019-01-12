import { Injectable } from '@angular/core';
import {User} from './api/user';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserRoleService {

  constructor(private http: HttpClient) { }
  // We are just allowed to get them, no creation or stuff

  getAll() {
    return this.http.get('/api/roles/');
  }

  getByName(roleName: String) {
    return this.http.get('/api/roles/' + roleName);
  }
}
