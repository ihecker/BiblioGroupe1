import { Routes } from '@angular/router';
import { AuteurPage } from './page/auteur/auteur-page/auteur-page';
import { GenrePage } from './page/genre/genre-page/genre-page';
import { EditeurPage } from './page/editeur/editeur-page/editeur-page';
import { CollectionPage } from './page/collection/collection-page/collection-page';

export const routes: Routes = [
  {path:"auteur", component: AuteurPage},
  {path:"genre", component: GenrePage},
  {path:"editeur", component: EditeurPage},
  {path:"collection", component: CollectionPage},
  {path:"", component:AuteurPage, pathMatch:"full"}
];
