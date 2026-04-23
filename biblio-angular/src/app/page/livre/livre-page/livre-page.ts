import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { Livre } from '../../../model/livre';
import { LivreService } from '../../../service/livre-service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-livre-page',
  imports: [ CommonModule, ReactiveFormsModule ],
  templateUrl: './livre-page.html',
  styleUrl: './livre-page.css',
})
export class LivrePage implements OnInit {
  private titleService: Title = inject(Title);
  private livreService: LivreService = inject(LivreService);
  private formBuilder: FormBuilder = inject(FormBuilder);


  protected livres$!: Observable<Livre[]>;
  private refresh$: Subject<void> = new Subject <void>();

  protected formLivre!: FormGroup;
  protected formTitreCtrl!: FormControl;
  protected formResumeCtrl!: FormControl;
  protected formAnneeCtrl!: FormControl;
  protected formIdAuteurCtrl!: FormControl;
  protected formIdEditeurCtrl!: FormControl;
  protected formIdCollectionCtrl!: FormControl;
  protected formIdGenreCtrl!: FormControl;


  ngOnInit(): void {
    this.titleService.setTitle("Gestion des livres");

    this.livres$ = this.refresh$.pipe(
      startWith(0),
      switchMap(() => this.livreService.findAll())
    );

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
}
