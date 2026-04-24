import { ChangeDetectorRef, Component, inject } from '@angular/core';
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
  private cdr: ChangeDetectorRef = inject(ChangeDetectorRef);

  protected avis$!: Observable<Avis[]>;
  protected refresh$: Subject<void> = new Subject<void>();
  protected livres$!: Observable<Livre[]>;
  protected update: boolean = false;

  protected formAvis!: FormGroup;
  protected formNoteCtrl!: FormControl;
  protected formCommentaireCtrl: FormControl = new FormControl();
  protected formDateCtrl: FormControl = new FormControl((new Date()).toISOString().substring(0, 10));
  protected formLivreCtrl!: FormControl;

  protected formUpdateIdCtrl: FormControl = new FormControl('');
  protected formUpdateNoteCtrl: FormControl = this.formBuilder.control('', [Validators.required, Validators.min(0), Validators.max(10)]);
  protected formUpdateCommentaireCtrl: FormControl = new FormControl('');
  protected formUpdateDateCtrl: FormControl = new FormControl('');
  protected formUpdateLivreCtrl: FormControl = this.formBuilder.control('', Validators.required);

  protected formUpdateAvis: FormGroup = this.formBuilder.group({
    id: this.formUpdateIdCtrl,
    note: this.formUpdateNoteCtrl,
    commentaire: this.formUpdateCommentaireCtrl,
    date: this.formUpdateDateCtrl,
    livre: this.formUpdateLivreCtrl
  });

  //protected updatedAvis: Avis = { id: 0, note: 0, commentaire: "", date: "", livreId: 0, livreTitre: "" };

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
    console.log(a);

    this.livreService.findById(a.livreId).subscribe(livre => {
      this.formUpdateAvis.setValue({
        id: a.id,
        note: a.note,
        commentaire: a.commentaire,
        date: a.date,
        livre: livre
      });

      this.update = true;
      this.cdr.detectChanges();
    });
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

    this.avisService.add(avis).subscribe(() => this.reload());
  }

  public updateAvis() {
    this.update = false;

    const avis: Avis = {
      id: this.formUpdateIdCtrl.value,
      note: this.formUpdateNoteCtrl.value,
      commentaire: this.formUpdateCommentaireCtrl.value,
      date: this.formUpdateDateCtrl.value,
      livreId: this.formUpdateLivreCtrl.value.id,
      livreTitre: this.formUpdateLivreCtrl.value.titre
    };

    this.avisService.update(avis).subscribe(() => this.reload());
  }

  public deleteAvis(id: number) {
    this.avisService.delete(id).subscribe(() => this.reload());
  }

}
