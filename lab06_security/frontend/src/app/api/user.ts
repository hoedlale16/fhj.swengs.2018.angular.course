import {UserRole} from './userRole';

export interface User {
  id?: number;
  username: string;
  password:string;
  // Password should be set onServer side. It is not allowed to get the encrypted password to the client.
  // Client can only modify first/lastname.
  firstname: string;
  lastname: string;

  role: UserRole;
}
