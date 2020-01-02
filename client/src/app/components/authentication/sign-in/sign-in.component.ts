import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../../services/authentication/authentication.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {SignInForm} from '../../../models/dto/sign-in-form';
import {ModalService} from '../../../services/modal.service';
import {Alert} from '../../../models/view/alert';


@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {
  form: FormGroup;
  alert: Alert;
  showEmailVerification = false;
  verificationEmail: string;
  isLoading = false;
  isPasswordShown = false;
  showPasswordRecovery = false;

  constructor(
    private authenticationService: AuthenticationService,
    private modalService: ModalService,
    private formBuilder: FormBuilder
  ) {
    this.form = this.formBuilder.group({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required,
        Validators.pattern('(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}')])
    });
  }

  ngOnInit() {
  }

  signIn() {
    if (this.form.invalid) {
      Object.keys(this.form.controls)
        .forEach(controlName => this.form.controls[controlName].markAsTouched());
      return;
    }
    this.isLoading = true;
    this.alert = null;
    this.showEmailVerification = false;
    const signInDto = new SignInForm();
    signInDto.email = this.form.controls.email.value;
    signInDto.password = this.form.controls.password.value;
    this.authenticationService.signIn(signInDto).subscribe(() => {
      this.isLoading = false;
      this.modalService.hideAuthentication();
    }, errorResponse => {
      this.isLoading = false;
      if (errorResponse.status === 404) {
        this.form.controls.email.setErrors({notExists: true});
      } else if (errorResponse.status === 423) {
        this.form.controls.password.setErrors({wrong: true});
      } else {
        this.alert = Alert.danger(errorResponse.statusText);
      }
    });
  }

  hasError(controlName: string, errorCode: string): boolean {
    return this.form.controls[controlName].touched && this.form.controls[controlName].hasError(errorCode);
  }

  activateAccount() {
    this.verificationEmail = this.form.controls.email.value;
    this.form.disable();
    this.showEmailVerification = true;
  }
}
