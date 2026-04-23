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
  protected formIdCtrl!: FormControl;


  ngOnInit(): void {
    this.titleService.setTitle("Gestion des livres");

    this.livres$ = this.refresh$.pipe(
      startWith(0),
      switchMap(() => this.livreService.findAll())
    );

  this.formTitreCtrl = this.formBuilder.control("Titre", Validators.required);
  this.formResumeCtrl = this.formBuilder.control("Résumé", Validators.required);
  this.formAnneeCtrl = this.formBuilder.control(2000, Validators.required);
  this.formIdCtrl = this.formBuilder.control(1, Validators.required);

    this.formLivre = this.formBuilder.group({
    titre: this.formTitreCtrl,
    resume: this.formResumeCtrl,
    annee: this.formAnneeCtrl,
    id: this.formIdCtrl,
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
      idAuteur: this.formIdCtrl.value,
      idEditeur: this.formIdCtrl.value,
      idCollection: this.formIdCtrl.value,
      idGenre: this.formIdCtrl.value,
    };

    this.livreService.add(livre).subscribe(() => this.reload());
  }

  
  public updateLivre(livre: Livre) {
  const updated: Livre = {
    id: livre.id,
    titre: this.formTitreCtrl.value,
    resume: this.formResumeCtrl.value,
    annee: this.formAnneeCtrl.value,
    idAuteur: this.formIdCtrl.value,
    idEditeur: this.formIdCtrl.value,
    idCollection: this.formIdCtrl.value,
    idGenre: this.formIdCtrl.value,
  };

  this.livreService.update(updated).subscribe(() => this.reload());
}



  public deleteLivre(livre: Livre) {
    this.livreService.deleteById(livre.id).subscribe(() => this.reload());
  }
}
