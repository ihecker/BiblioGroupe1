import { TestBed } from '@angular/core/testing';

import { AvisPage } from './avis-page';

describe('AvisPage', () => {
  let service: AvisPage;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AvisPage);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
