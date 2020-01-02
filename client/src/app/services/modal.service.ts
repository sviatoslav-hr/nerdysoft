import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ModalService {
  constructor() {
  }

  private authenticationDisplayed = false;
  private authenticationSignInSelected = true;

  get isAuthenticationSignInSelected(): boolean {
    return this.authenticationSignInSelected;
  }

  set isAuthenticationSignInSelected(value: boolean) {
    this.authenticationSignInSelected = value;
  }

  get isAuthenticationDisplayed(): boolean {
    return this.authenticationDisplayed;
  }

  public showAuthentication() {
    this.authenticationDisplayed = true;
  }

  public hideAuthentication() {
    this.authenticationDisplayed = false;
  }
}
