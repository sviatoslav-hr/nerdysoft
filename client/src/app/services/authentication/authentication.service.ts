import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SignInForm} from '../../models/dto/sign-in-form';
import {ApiUrls} from '../../const/api-urls';
import {map} from 'rxjs/operators';
import {SignUpForm} from '../../models/dto/sign-up-form';
import {JwtResponse} from '../../models/dto/jwt-response';
import {Observable} from 'rxjs';
import {StorageHelper} from '../../helpers/storage-helper';
import {User} from '../../models/entity/user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private signedInUser: User;
  private isSigningIn = false;

  constructor(
    private http: HttpClient
  ) {
  }

  public get user(): User {
    return this.signedInUser;
  }

  public get authenticated(): boolean {
    return !!this.user;
  }

  public get signingIn(): boolean {
    return this.isSigningIn;
  }

  public requestAuthenticatedUser(): void {
    this.isSigningIn = true;
    this.requestPrincipal();
  }

  private requestPrincipal(): void {
    if (!this.signedInUser) {
      this.getAuthenticatedUser().subscribe(principal => {
        this.isSigningIn = false;
        this.signedInUser = principal;
      }, () => {
        this.isSigningIn = false;
        setTimeout(() => this.requestPrincipal(), 5000);
      });
    }
  }

  public signIn(form: SignInForm): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(ApiUrls.SIGN_IN, form)
      .pipe(map(response => {
        if (response.accessToken) {
          StorageHelper.token = response.accessToken;
          this.requestPrincipal();
        }
        return response;
      }));
  }

  public signUp(form: SignUpForm): Observable<boolean> {
    return this.http.post<boolean>(ApiUrls.SIGN_UP, form);
  }

  private getAuthenticatedUser(): Observable<User> {
    return this.http.get<User>(ApiUrls.USER_AUTHENTICATED);
  }

  public logOut() {
    this.signedInUser = null;
    StorageHelper.clear();
  }
}
