import {environment} from '../../environments/environment';

export class ApiUrls {
  public static SIGN_IN = `${environment.apiUrl}/authentication/sign-in`;
  public static SIGN_UP = `${environment.apiUrl}/authentication/sign-up`;
  public static USER_AUTHENTICATED = `${environment.apiUrl}/users/authenticated`;
  public static TASKS = `${environment.apiUrl}/tasks/`;
  public static TASKS_SHARED = `${ApiUrls.TASKS}shared`;
  public static TASKS_SHARE = `${ApiUrls.TASKS}share`;
  public static TASKS_ACCEPT = `${ApiUrls.TASKS}accept/`;
  public static TASKS_DECLINE = `${ApiUrls.TASKS}decline/`;
}
