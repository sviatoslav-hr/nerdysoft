import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from './services/authentication/authentication.service';
import {StorageHelper} from './helpers/storage-helper';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor(
    private authenticationService: AuthenticationService
  ) {
  }

  get authService(): AuthenticationService {
    return this.authenticationService;
  }

  ngOnInit(): void {
    if (StorageHelper.token) {
      this.authenticationService.requestAuthenticatedUser();
    }
  }
}
