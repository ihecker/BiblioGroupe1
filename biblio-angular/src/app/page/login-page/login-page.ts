import {CommonModule} from '@angular/common';
import {Component, inject, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, ValidationErrors, ValidatorFn, Validators} from '@angular/forms';
import {Title} from '@angular/platform-browser';
import {Router} from '@angular/router';
import {startWith, Subject, switchMap} from 'rxjs';

import {AuthRequest} from '../../dto/auth-request';
import {AuthService} from '../../service/auth/auth-service';

@Component({
  selector: 'app-login-page',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './login-page.html',
  styleUrl: './login-page.css',
})
export class LoginPage implements OnInit {
  private authService: AuthService = inject(AuthService);
  private router: Router = inject(Router);
  private titleService: Title = inject(Title);
  private formBuilder: FormBuilder = inject(FormBuilder);

  lowerCaseValidator(control: FormControl): {[key: string]: boolean}|null {
    const value = control.value;

    if (value && !/^[a-z]+$/.test(value)) {
      return {lowercase: true};
    }

    return null;
  }



  // protected formAuth: AuthRequest = { } as AuthRequest;
  protected formConnexion!: FormGroup;
  protected formLoginCtrl!: FormControl;
  protected formPasswordCtrl!: FormControl;

  private refresh$: Subject<void> = new Subject<void>();


  ngOnInit(): void {
    this.titleService.setTitle('Authentification');



    this.formLoginCtrl =
        this.formBuilder.control('formateur', Validators.required);

    this.formPasswordCtrl =
        this.formBuilder.control('123456', Validators.required);


    this.formConnexion = this.formBuilder.group({

      username: this.formLoginCtrl,
      password: this.formPasswordCtrl
    });
  }


  public connexion() {
    const formAuth: AuthRequest = {
      username: this.formLoginCtrl.value,
      password: this.formPasswordCtrl.value
    };
    this.authService.auth(formAuth).subscribe(resp => {
      if (resp.token.length > 0) {
        this.authService.token = resp.token;
        console.log(resp.token);
        this.router.navigate(['/livres']);
      }
    });
  }
}
