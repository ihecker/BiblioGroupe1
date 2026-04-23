import { Component, inject } from '@angular/core';
import {  FormBuilder, FormsModule,ReactiveFormsModule,Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Auteur } from '../../../model/auteur';
import { Observable } from 'rxjs';
import { AuteurService } from '../../../service/auteur-service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-auteur-page',
  imports: [ ReactiveFormsModule,FormsModule, CommonModule],
  templateUrl: './auteur-page.html',
  styleUrl: './auteur-page.css',
})
export class AuteurPage {
   


  auteurs$!: Observable<Auteur[]>;
  newAuteur: Auteur = {nom: '', prenom: '', nationalite: ''};
  router: Router = inject(Router);

  constructor(private auteurService: AuteurService) {}

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.loadAuteur();
  }

  reload() {
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
    this.router.navigate([ '/auteur' ]);
    });
  }

  loadAuteur() {
   this.auteurs$ = this.auteurService.getAll();
  }

  addAuteur() {
    this.auteurService.add(this.newAuteur).subscribe(()  => {this.reload()});
  }

  deleteAuteur(id: number) {
    this.auteurService.delete(id).subscribe(()  => {this.reload()});
  }

}
