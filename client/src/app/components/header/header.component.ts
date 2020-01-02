import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../services/authentication/authentication.service';
import {ModalService} from '../../services/modal.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(
    private authService: AuthenticationService,
    private modalService: ModalService
  ) {
  }

  ngOnInit() {
  }

  logOut() {
    this.authService.logOut();
  }

  showAuthModal(signIn: boolean) {
    this.modalService.isAuthenticationSignInSelected = signIn;
    this.modalService.showAuthentication();
  }
}
