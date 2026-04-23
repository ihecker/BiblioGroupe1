import { HttpInterceptorFn } from '@angular/common/http';

export const urlInterceptor: HttpInterceptorFn = (req, next) => {

  return next(
    req.clone({
      url: 'http://localhost:8080/api' + req.url,
    }),
  );
};
