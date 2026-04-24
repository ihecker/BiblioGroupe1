import { Routes } from '@angular/router';
import { AuteurPage } from './page/auteur/auteur-page/auteur-page';
import { AvisPage } from './page/avis/avis-page/avis-page';
import { CollectionPage } from './page/collection/collection-page/collection-page';
import { EditeurPage } from './page/editeur/editeur-page/editeur-page';
import { GenrePage } from './page/genre/genre-page/genre-page';
import { LivrePage } from './page/livre/livre-page/livre-page';
import { HomePage } from './page/home-page/home-page';
import { LoginPage } from './page/login-page/login-page';
import { authGuard } from './guard/auth-guard';

export const routes: Routes = [
  { path: 'home', component: HomePage, canActivate: [authGuard] },
  { path: 'login', component: LoginPage },
  { path: 'livres', component: LivrePage, canActivate: [authGuard] },
  { path: 'auteur', component: AuteurPage, canActivate: [authGuard] },
  { path: 'genre', component: GenrePage, canActivate: [authGuard] },
  { path: 'editeur', component: EditeurPage, canActivate: [authGuard] },
  { path: 'avis', component: AvisPage, canActivate: [authGuard] },
  { path: 'collection', component: CollectionPage, canActivate: [authGuard] },
  { path: '', component: LoginPage, pathMatch: 'full' },
];
