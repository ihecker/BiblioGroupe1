import { Component, inject } from '@angular/core';
import {  FormBuilder, FormControl, FormGroup, FormsModule,ReactiveFormsModule,Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Auteur } from '../../../model/auteur';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { AuteurService } from '../../../service/auteur-service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-auteur-page',
  imports: [ ReactiveFormsModule,FormsModule, CommonModule],
  templateUrl: './auteur-page.html',
  styleUrl: './auteur-page.css',
})
export class AuteurPage {

  private auteurService = inject(AuteurService);
  private fb = inject(FormBuilder);
  
  formBuilder: FormBuilder = inject(FormBuilder);
  auteurs$!: Observable<Auteur[]>;
  //newAuteur: Auteur = {nom: '', prenom: '', nationalite: ''};
  router: Router = inject(Router);

  refresh$ = new Subject<void>();
  formAuteur!: FormGroup;
  selectedAuteur: Auteur | null | undefined;

  //protected formNomCtrl!: FormControl;
  //protected formPrenomCtrl!: FormControl;
  //protected formNationaliteCtrl!: FormControl;
  



  ngOnInit(): void {
    
    this.formAuteur = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      nationalite: ['', Validators.required]
    });

    this.auteurs$ = this.refresh$.pipe(
      startWith(void 0),
      switchMap(() => this.auteurService.getAll())
    );
  }

  reload() {
    this.refresh$.next();

    //this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
    //this.router.navigate([ '/auteur' ]);
    //});
  }

  loadAuteur() {
   this.auteurs$ = this.auteurService.getAll();
  }
  
  deleteAuteur(id: number) {
    this.auteurService.delete(id).subscribe(()  => {this.reload()});
  }

  
  submitAuteur() {

  if (this.formAuteur.invalid) {
    this.formAuteur.markAllAsTouched();
    return;
  }

  const auteur: Auteur = {
    id: this.selectedAuteur ? this.selectedAuteur.id! : 0,
    ...this.formAuteur.value
  };

  const request = this.selectedAuteur
    ? this.auteurService.update(auteur)
    : this.auteurService.add(auteur);

  request.subscribe(() => {
    this.reload();
    this.formAuteur.reset();
    this.selectedAuteur = null;
  });
}

modifierAuteur(auteur: Auteur) {
  this.selectedAuteur = auteur;

  this.formAuteur.patchValue({
    nom: auteur.nom,
    prenom: auteur.prenom,
    nationalite: auteur.nationalite
  });
}

  cancelEdit() {
    this.selectedAuteur = null;
    this.formAuteur.reset();
  }

}
