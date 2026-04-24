import { Livre } from './livre';

export interface EditeurWithLivre {
  nom: string;
  pays: string;
  livres: Livre[];
}
