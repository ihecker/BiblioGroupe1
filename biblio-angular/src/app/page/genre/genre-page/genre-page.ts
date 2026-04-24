import { Component, inject, OnInit } from '@angular/core';
import { GenreService } from '../../../service/genre/genre-service';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Genre } from '../../../model/genre';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-genre-page',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './genre-page.html',
  styleUrl: './genre-page.css',
})
export class GenrePage  {
  private genreService: GenreService = inject(GenreService);
  private formBuilder: FormBuilder = inject(FormBuilder);
  protected genres$!: Observable<Genre[]>;
  private refresh$: Subject<void> = new Subject<void>();

  protected formGenre!: FormGroup;
  protected formLibelleCtrl!: FormControl;

  ngOnInit(): void {
    this.genres$ = this.refresh$.pipe(
      startWith(0),
      switchMap(() => this.genreService.findAll()),
    );

    this.formLibelleCtrl = this.formBuilder.control('', Validators.required);

    this.formGenre = this.formBuilder.group({
      libelle: this.formLibelleCtrl,
    });
  }

  private reload() {
    this.refresh$.next();
  }

  public addGenre() {
    const genre: Genre = {
      id: 0,
      libelle: this.formLibelleCtrl.value,
    };
    genre.libelle = this.formLibelleCtrl.value;

    this.genreService.insert(genre).subscribe(() => this.reload());
  }

  public deleteGenre(genre: Genre) {
    this.genreService.delete(genre.id).subscribe(() => this.reload());
  }
}
