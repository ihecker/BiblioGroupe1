import { Component, inject, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { CollectionService } from '../../../service/collection/collection-service';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { Collection } from '../../../model/collection';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-collection-page',
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  templateUrl: './collection-page.html',
  styleUrl: './collection-page.css',
})
export class CollectionPage implements OnInit{

private titleService: Title = inject(Title);
private collectionService: CollectionService = inject(CollectionService);
// permet de faciliter la manipulation de formulaire(controle...)
private formBuilder: FormBuilder = inject(FormBuilder);

protected collection$!: Observable<Collection[]>;
private refresh$: Subject<void> = new Subject<void>();
protected collectionEnEdition: Collection = {} as Collection;

protected formCollection!: FormGroup;
protected formNomCtrl!: FormControl;

// s'execute quand on affiche la page
  ngOnInit(): void {
// donne le nom a l'onglet
    this.titleService.setTitle("Liste des collections");

    // a chaque fois que je refresh, ca recharge la liste de collection
    this.collection$ = this.refresh$.pipe(
      startWith(0),
      switchMap(()=> this.collectionService.findAll())
    );

    this.formNomCtrl = this.formBuilder.control("", Validators.required);
    
    this.formCollection = this.formBuilder.group({
      nom: this.formNomCtrl
    });
  }

  private reload() {
    this.refresh$.next();
}

public addCollection() {
  const collection: Collection = {
    id: 0,
    nom: this.formNomCtrl.value
  };
  this.collectionService.add(collection).subscribe(()=> this.reload());
}

public updateCollection(collection: Collection){
this.collectionEnEdition =  {} as Collection;
this.collectionEnEdition.id= collection.id;
this.collectionEnEdition.nom = collection.nom; 
}

  annuler() {
  this.collectionEnEdition.id = -1;
}

public deleteCollection(collection: Collection) {
  this.collectionService.deleteById(collection.id).subscribe(()=> this.reload());
}

 editer(collection: Collection) {
    this.collectionEnEdition = { ...collection};
  }
  
sauvegarder() {
  this.collectionService.update(this.collectionEnEdition!.id, this.collectionEnEdition!).subscribe(() => {
    this.collectionEnEdition.id = -1;
    this.reload();
  });
}


}

