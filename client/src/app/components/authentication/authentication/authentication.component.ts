import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../../services/authentication/authentication.service';
import {ModalService} from '../../../services/modal.service';

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.css']
})
export class AuthenticationComponent implements OnInit {
  constructor(
    private authService: AuthenticationService,
    private modalService: ModalService
  ) {
  }

  ngOnInit() {
  }

  get isSignedUp(): boolean {
    return this.modalService.isAuthenticationSignInSelected;
  }

  set isSignedUp(value: boolean) {
    this.modalService.isAuthenticationSignInSelected = value;
  }

  close() {
    this.modalService.hideAuthentication();
  }

  get ms(): ModalService {
    return this.modalService;
  }
}
