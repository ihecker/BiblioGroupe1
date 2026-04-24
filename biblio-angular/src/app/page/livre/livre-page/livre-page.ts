import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { Livre } from '../../../model/livre';
import { LivreService } from '../../../service/livre-service';
import { CommonModule } from '@angular/common';
import { EditeurResponse } from '../../../model/editeur-response';
import { Auteur } from '../../../model/auteur';
import { Collection } from '../../../model/collection';
import { Genre } from '../../../model/genre';
import { GenreService } from '../../../service/genre/genre-service';
import { AuteurService } from '../../../service/auteur-service';
import { EditeurService } from '../../../service/editeur/editeur-service';
import { CollectionService } from '../../../service/collection/collection-service';

@Component({
  selector: 'app-livre-page',
  imports: [ CommonModule, ReactiveFormsModule, FormsModule],
  templateUrl: './livre-page.html',
  styleUrl: './livre-page.css',
})
export class LivrePage implements OnInit {
  private titleService: Title = inject(Title);
  private livreService: LivreService = inject(LivreService);
  private formBuilder: FormBuilder = inject(FormBuilder);


  protected livres$!: Observable<Livre[]>;
  private refresh$: Subject<void> = new Subject <void>();
  protected auteurs$!: Observable<Auteur[]>;
  protected editeurs$!: Observable<EditeurResponse[]>;
  protected collections$!: Observable<Collection[]>;
  protected genres$!: Observable<Genre[]>;
  protected livreEnEdition: Livre | null = null;




  protected formLivre!: FormGroup;
  protected formTitreCtrl!: FormControl;
  protected formResumeCtrl!: FormControl;
  protected formAnneeCtrl!: FormControl;
  protected formIdAuteurCtrl!: FormControl;
  protected formIdEditeurCtrl!: FormControl;
  protected formIdCollectionCtrl!: FormControl;
  protected formIdGenreCtrl!: FormControl;
private auteurService: AuteurService = inject(AuteurService);
private editeurService: EditeurService = inject(EditeurService);
private collectionService: CollectionService = inject(CollectionService);
private genreService: GenreService = inject(GenreService);


  ngOnInit(): void {
    this.titleService.setTitle("Gestion des livres");

    this.livres$ = this.refresh$.pipe(
      startWith(0),
      switchMap(() => this.livreService.findAll())
    );
    this.auteurs$ = this.auteurService.getAll();
    this.editeurs$ = this.editeurService.findAll();
    this.collections$ = this.collectionService.findAll();
    this.genres$ = this.genreService.findAll();


this.formTitreCtrl = this.formBuilder.control('');
this.formResumeCtrl = this.formBuilder.control('');
this.formAnneeCtrl = this.formBuilder.control(null, Validators.min(1));
this.formIdAuteurCtrl = this.formBuilder.control(null, Validators.min(1));
this.formIdEditeurCtrl = this.formBuilder.control(null, Validators.min(1));
this.formIdCollectionCtrl = this.formBuilder.control(null, Validators.min(1));
this.formIdGenreCtrl = this.formBuilder.control(null, Validators.min(1));

this.formLivre = this.formBuilder.group({
  titre: this.formTitreCtrl,
  resume: this.formResumeCtrl,
  annee: this.formAnneeCtrl,
  idAuteur: this.formIdAuteurCtrl,
  idEditeur: this.formIdEditeurCtrl,
  idCollection: this.formIdCollectionCtrl,
  idGenre: this.formIdGenreCtrl,
});

  }

  private reload() {
    this.refresh$.next();
  }

  public addLivre() {
    const livre: Livre = {
      id: 0,
      titre: this.formTitreCtrl.value,
      resume: this.formResumeCtrl.value,
      annee: this.formAnneeCtrl.value,
      idAuteur: this.formIdAuteurCtrl.value,
      idEditeur: this.formIdEditeurCtrl.value,
      idCollection: this.formIdCollectionCtrl.value,
      idGenre: this.formIdGenreCtrl.value,
    };

    this.livreService.add(livre).subscribe(() => this.reload());
  }

  
  public updateLivre(livre: Livre) {
  const updated: Livre = {
    id: livre.id,
    titre: this.formTitreCtrl.value,
    resume: this.formResumeCtrl.value,
    annee: this.formAnneeCtrl.value,
    idAuteur: this.formIdAuteurCtrl.value,
    idEditeur: this.formIdEditeurCtrl.value,
    idCollection: this.formIdCollectionCtrl.value,
    idGenre: this.formIdGenreCtrl.value,
  };

  this.livreService.update(updated).subscribe(() => this.reload());
}



  public deleteLivre(livre: Livre) {
    this.livreService.deleteById(livre.id).subscribe(() => this.reload());
  }

    editer(livre: Livre) {
    this.livreEnEdition = { ...livre};
  }

  sauvegarder() {
  this.livreService.update(this.livreEnEdition!).subscribe(() => {
    this.livreEnEdition = null;
    this.reload();
  });
}

  annuler() {
  this.livreEnEdition = null;
}
}
