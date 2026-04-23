import { Component, OnInit } from '@angular/core';
import { AsyncPipe, NgForOf } from '@angular/common';
import { EditeurService } from '../../../service/editeur/editeur-service';
import { Router } from '@angular/router';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { EditeurResponse } from '../../../model/editeur-response';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { EditeurRequest } from '../../../model/editeur-request';

@Component({
  selector: 'app-editeur-page',
  imports: [NgForOf, AsyncPipe, ReactiveFormsModule],
  templateUrl: './editeur-page.html',
  styleUrl: './editeur-page.css',
})
export class EditeurPage implements OnInit {
  protected editeurs$!: Observable<EditeurResponse[]>;
  private refresh$: Subject<void> = new Subject<void>();

  protected idModif?: number;

  protected formEditeur!: FormGroup;
  protected formNomCtrl!: FormControl;
  protected formPaysCtrl!: FormControl;

  constructor(
    protected editeurService: EditeurService,
    protected router: Router,
    protected formBuilder: FormBuilder,
  ) {}

  ngOnInit(): void {
    this.formNomCtrl = this.formBuilder.control('', Validators.required);
    this.formPaysCtrl = this.formBuilder.control('', Validators.required);
    this.formEditeur = this.formBuilder.group({
      nom: this.formNomCtrl,
      pays: this.formPaysCtrl,
    });

    this.editeurs$ = this.refresh$.pipe(
      startWith(0),
      switchMap(() => this.editeurService.findAll()),
    );
  }

  protected activateModification(id: number): void {
    this.idModif = id;
  }

  protected deactivateModification(): void {
    this.idModif = undefined;
  }

  protected addEditeur(): void {
    const editeurRequest: EditeurRequest = {} as EditeurRequest;
    editeurRequest.nom = this.formNomCtrl.value;
    editeurRequest.pays = this.formPaysCtrl.value;
    this.editeurService.addEditeur(editeurRequest).subscribe(() => this.reload());
  }

  private reload(): void {
    this.refresh$.next();
  }
}
