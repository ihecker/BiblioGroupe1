import { Component, inject } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { AvisService } from '../../../service/avis/avis-service';
import { AbstractControl, FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, ValidationErrors, Validators } from '@angular/forms';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { Avis } from '../../../model/avis';
import { CommonModule } from '@angular/common';
import { Livre } from '../../../model/livre';
import { LivreService } from '../../../service/livre-service';

@Component({
  selector: 'app-avis-page',
  imports: [FormsModule, CommonModule, ReactiveFormsModule],
  templateUrl: './avis-page.html',
  styleUrl: './avis-page.css',
})
export class AvisPage {
  private titleService: Title = inject(Title);
  private avisService: AvisService = inject(AvisService);
  private livreService: LivreService = inject(LivreService);
  private formBuilder: FormBuilder = inject(FormBuilder);

  protected avis$!: Observable<Avis[]>;
  protected refresh$: Subject<void> = new Subject<void>();
  protected livres$!: Observable<Livre[]>;
  protected update: boolean = false;

  protected formAvis!: FormGroup;
  protected formNoteCtrl!: FormControl;
  protected formCommentaireCtrl: FormControl = new FormControl();
  protected formDateCtrl: FormControl = new FormControl((new Date()).toISOString().substring(0, 10));
  protected formLivreCtrl!: FormControl;

  protected updatedAvis: Avis = { id: 0, note: 0, commentaire: "", date: "", livreId: 0, livreTitre: "" };

  ngOnInit(): void {
    this.titleService.setTitle("Avis");

    this.avis$ = this.refresh$.pipe(
      startWith(0), switchMap(() => this.avisService.findAll())
    );
    this.livres$ = this.livreService.findAll();

    const noteValueValidator = (control: AbstractControl): ValidationErrors | null => {
      if (control.value < 0 || control.value > 10) {
        return { value: true }
      }
      return null;
    };

    this.formNoteCtrl = this.formBuilder.control("", [Validators.required, noteValueValidator]);
    //this.formDateCtrl = this.formBuilder.control("", Validators.required);
    //this.formCommentaireCtrl = this.formBuilder.control("", Validators.required);
    this.formLivreCtrl = this.formBuilder.control("", Validators.required);

    this.formAvis = this.formBuilder.group({
      note: this.formNoteCtrl,
      commentaire: this.formCommentaireCtrl,
      date: this.formDateCtrl,
      livre: this.formLivreCtrl
    });

  }

  public compareById(a: any, b: any): boolean {
    return a && b ? a.id === b.id : a === b;
  }

  private reload() {
    this.refresh$.next();
  }

  public updatableAvis(a: Avis) {
    this.updatedAvis.id = a.id;
    this.updatedAvis.note = a.note;
    this.updatedAvis.commentaire = a.commentaire;
    this.updatedAvis.date = this.formDateCtrl.value;
    this.updatedAvis.livreId = a.livreId;
    this.updatedAvis.livreTitre = a.livreTitre;
    this.update = true;
  }

  public addAvis() {
    const avis: Avis = {
      id: 0,
      note: this.formNoteCtrl.value,
      commentaire: this.formCommentaireCtrl.value,
      date: this.formDateCtrl.value,
      livreId: this.formLivreCtrl.value.id,
      livreTitre: this.formLivreCtrl.value.titre
    };

    console.log(avis);

    this.avisService.add(avis).subscribe(() => this.reload());
  }

  public updateAvis() {
    this.update = false;
    this.avisService.update({ id: this.updatedAvis.id, note: this.updatedAvis.note, commentaire: this.updatedAvis.commentaire, date: this.updatedAvis.date, livreId: this.updatedAvis.livreId, livreTitre: this.updatedAvis.livreTitre }).subscribe(() => this.reload());
  }

  public deleteAvis(id: number) {
    this.avisService.delete(id).subscribe(() => this.reload());
  }

}
