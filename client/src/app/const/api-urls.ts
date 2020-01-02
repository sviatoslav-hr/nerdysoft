import {environment} from '../../environments/environment';

export class ApiUrls {
  public static SIGN_IN = `${environment.apiUrl}/authentication/sign-in`;
  public static SIGN_UP = `${environment.apiUrl}/authentication/sign-up`;
  public static USER_AUTHENTICATED = `${environment.apiUrl}/user/authenticated`;
}
