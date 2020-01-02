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

  constructor(
    private http: HttpClient
  ) {
  }

  public get user(): User {
    return this.signedInUser;
  }

  public get isLogged(): boolean {
    return !!this.user;
  }

  public requestSignedInPrincipal(): void {
    this.requestPrincipal();
  }

  private requestPrincipal(): void {
    if (!this.signedInUser) {
      this.getAuthenticatedUser().subscribe(principal => {
        console.log(principal);
        this.signedInUser = principal;
      }, () => {
        setTimeout(() => this.requestPrincipal(),
          5000);
      });
    }
  }

  public signIn(form: SignInForm): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(ApiUrls.SIGN_IN, form)
      .pipe(map(response => {
        console.log(response);
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
