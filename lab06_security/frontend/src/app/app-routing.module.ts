import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ActorFormComponent} from './actor-form/actor-form.component';
import {ActorListComponent} from './actor-list/actor-list.component';
import {LoginComponent} from './login/login.component';
import {AuthGuard} from './auth.guard';
import {UserListComponent} from './user-list/user-list.component';
import {UserFormComponent} from './user-form/user-form.component';
import {AuthAdminGuard} from './auth-admin.guard';

const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'actor-form', component: ActorFormComponent, canActivate: [AuthGuard]},
  {path: 'actor-list', component: ActorListComponent, canActivate: [AuthGuard]},
  {path: 'actor-form/:id', component: ActorFormComponent, canActivate: [AuthGuard]},
  {path: 'login', component: LoginComponent},
  {path: 'user-list', component: UserListComponent, canActivate: [AuthAdminGuard]},
  {path: 'user-form', component: UserFormComponent, canActivate: [AuthAdminGuard]},
  {path: 'user-form/:username', component: UserFormComponent, canActivate: [AuthAdminGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
