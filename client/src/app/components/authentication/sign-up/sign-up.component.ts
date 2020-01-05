import {Component, OnInit} from '@angular/core';
import {SignUpForm} from '../../../models/dto/sign-up-form';
import {AuthenticationService} from '../../../services/authentication/authentication.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ModalService} from '../../../services/modal.service';
import {MustMatch} from '../../../helpers/must-match.validator';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  form: FormGroup;
  errorMessage: string;
  isLoading = false;
  isPasswordShown = false;
  isConfirmPasswordShown = false;
  isSigningIn = false;

  constructor(
    private authenticationService: AuthenticationService,
    private modalService: ModalService,
    private formBuilder: FormBuilder
  ) {
    this.form = this.formBuilder.group({
      username: new FormControl('', [Validators.required,
        Validators.pattern('(^[А-ЯЄІЇа-яєіїA-Za-z 0-9]+$)')]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required,
        Validators.pattern('(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{3,}'),
        Validators.minLength(8)]),
      confirmPassword: new FormControl('', [Validators.required])
    }, {validator: MustMatch('password', 'confirmPassword')});
  }

  ngOnInit() {
  }

  signUp() {
    if (this.form.invalid) {
      Object.keys(this.form.controls)
        .forEach(controlName => this.form.controls[controlName].markAsTouched());
      return;
    }
    this.isLoading = true;
    const signUpDto = new SignUpForm();
    signUpDto.username = this.form.controls.username.value;
    signUpDto.email = this.form.controls.email.value;
    signUpDto.password = this.form.controls.password.value;
    this.authenticationService.signUp(signUpDto)
      .subscribe(() => {
        this.isLoading = false;
        this.signIn(signUpDto.email, signUpDto.password);
      }, (errorResponse) => {
        this.isLoading = false;
        if (errorResponse.status === 409) {
          this.form.controls.email.setErrors({occupied: true});
        } else {
          this.errorMessage = errorResponse.error;
        }
      });
  }

  hasError(controlName: string, errorCode: string): boolean {
    return this.form.controls[controlName].touched && this.form.controls[controlName].hasError(errorCode);
  }

  private signIn(email: string, password: string) {
    this.authenticationService.signIn({email, password}).subscribe(() => {
      this.modalService.hideAuthentication();
    }, () => {
      this.modalService.isAuthenticationSignInSelected = true;
    });
  }
}
