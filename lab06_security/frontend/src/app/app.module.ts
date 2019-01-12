import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {ActorFormComponent} from './actor-form/actor-form.component';
import {FormsModule} from '@angular/forms';
import { ActorListComponent } from './actor-list/actor-list.component';
import {JwtModule} from '@auth0/angular-jwt';
import { LoginComponent } from './login/login.component';
import { UserListComponent } from './user-list/user-list.component';
import { UserFormComponent } from './user-form/user-form.component';
import { MenuManagementComponent } from './menu-management/menu-management.component';

export function tokenGetter() {
  return localStorage.getItem('access_token');
}

@NgModule({
  declarations: [
    AppComponent,
    ActorFormComponent,
    ActorListComponent,
    LoginComponent,
    UserListComponent,
    UserFormComponent,
    MenuManagementComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    JwtModule.forRoot( {
      config: {
        tokenGetter: tokenGetter,
        whitelistedDomains: ['localhost:4200']
      }
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule {
}
