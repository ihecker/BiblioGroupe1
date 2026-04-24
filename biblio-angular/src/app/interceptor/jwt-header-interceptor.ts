import { HttpInterceptorFn } from '@angular/common/http';

export const jwtHeaderInterceptorInterceptor: HttpInterceptorFn = (req, next) => {


  const reqAuth = req.clone({
    setHeaders:{Authorization:`Bearer ${sessionStorage.getItem("token")}`}
  })

  return next(reqAuth);
};
