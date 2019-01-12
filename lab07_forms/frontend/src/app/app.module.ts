import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {ActorFormComponent} from './actor-form/actor-form.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ActorListComponent} from './actor-list/actor-list.component';
import {JwtModule} from '@auth0/angular-jwt';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { RatingModule } from 'ngx-bootstrap/rating';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { NgxSelectModule } from 'ngx-select-ex';

import { defineLocale } from 'ngx-bootstrap/chronos';
import { deLocale } from 'ngx-bootstrap/locale';
import {UniqueActorNickNameDirective} from './api/validators/uniqueActorNickNameValidator';
defineLocale('de', deLocale);

export function tokenGetter() {
  return localStorage.getItem('access_token');
}

@NgModule({
  declarations: [
    AppComponent,
    ActorFormComponent,
    ActorListComponent,
    LoginComponent,
    LogoutComponent,
    UniqueActorNickNameDirective
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        whitelistedDomains: ['localhost:4200']
      }
    }),
    RatingModule.forRoot(),
    BsDatepickerModule.forRoot(),
    NgxSelectModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
