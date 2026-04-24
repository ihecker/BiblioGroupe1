import { TestBed } from '@angular/core/testing';

import { LoginPage } from './login-page';

describe('LoginPage', () => {
  let service: LoginPage;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoginPage);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
