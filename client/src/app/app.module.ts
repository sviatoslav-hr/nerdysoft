import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {RoutingModule} from './modules/routing/routing.module';
import {HomeComponent} from './components/home/home.component';
import {NotFoundComponent} from './components/not-found/not-found.component';
import {AuthenticationComponent} from './components/authentication/authentication/authentication.component';
import {SignInComponent} from './components/authentication/sign-in/sign-in.component';
import {SignUpComponent} from './components/authentication/sign-up/sign-up.component';
import {httpInterceptorProviders} from './services/authentication/authentication-interceptor';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http';
import {MatFormFieldModule, MatInputModule} from '@angular/material';
import { HeaderComponent } from './components/header/header.component';
import { TaskComponent } from './components/task/task.component';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NotFoundComponent,
    AuthenticationComponent,
    SignInComponent,
    SignUpComponent,
    HeaderComponent,
    TaskComponent
  ],
  imports: [
    BrowserModule,
    RoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    FontAwesomeModule
  ],
  providers: [
    httpInterceptorProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
