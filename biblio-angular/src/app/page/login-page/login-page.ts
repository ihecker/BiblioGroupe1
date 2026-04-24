import { inject, Component } from '@angular/core';
import { AuthService } from '../../service/auth/auth-service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthRequest } from '../../dto/auth-request';

@Component({
  imports: [],
  templateUrl: './login-page.html',
  styleUrl: './login-page.css',
})
export class LoginPage {
  private authService: AuthService = inject(AuthService);
  private router: Router = inject(Router);
  private formBuilder: FormBuilder = inject(FormBuilder);

  protected formAuth!: FormGroup;
  protected formUsernameCtrl!: FormControl;
  protected formPasswordCtrl!: FormControl;

  ngOnInit(): void {
    this.formUsernameCtrl = this.formBuilder.control('', Validators.required);
    this.formPasswordCtrl = this.formBuilder.control('', Validators.required);

    this.formAuth = this.formBuilder.group({
      username: this.formUsernameCtrl,
      password: this.formPasswordCtrl,
    });
  }

  public connexion() {
    const authRequest: AuthRequest = {
      login: this.formUsernameCtrl.value,
      password: this.formPasswordCtrl.value,
    };

    this.authService.auth(authRequest).subscribe((resp) => {
      if (resp.success) {
        this.authService.token = resp.token;
        this.router.navigate(['/home']);
      }
    });
  }
}
