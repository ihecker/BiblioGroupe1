import { Routes } from '@angular/router';
import { AuteurPage } from './page/auteur/auteur-page/auteur-page';
import { AvisPage } from './page/avis/avis-page/avis-page';
import { CollectionPage } from './page/collection/collection-page/collection-page';
import { EditeurPage } from './page/editeur/editeur-page/editeur-page';
import { GenrePage } from './page/genre/genre-page/genre-page';
import { LivrePage } from './page/livre/livre-page/livre-page';

export const routes: Routes = [
  {path:"livres", component: LivrePage},
  {path:"auteur", component: AuteurPage},
  {path:"genre", component: GenrePage},
  {path:"editeur", component: EditeurPage},
  {path:"avis", component: AvisPage},
  {path:"collection", component: CollectionPage},
  {path:"", component:AuteurPage, pathMatch:"full"}
];
 