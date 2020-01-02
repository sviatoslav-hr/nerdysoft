const TOKEN_KEY = 'NerdysoftAuthenticationToken';

export class StorageHelper {

  constructor() {
  }

  static clear() {
    window.localStorage.clear();
  }

  public static set token(token: string) {
    window.localStorage.removeItem(TOKEN_KEY);
    window.localStorage.setItem(TOKEN_KEY, token);
  }

  public static get token(): string {
    return localStorage.getItem(TOKEN_KEY);
  }
}
