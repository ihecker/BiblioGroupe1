import { Component, inject, OnInit } from '@angular/core';
import { GenreService } from '../../../service/genre/genre-service';

@Component({
  selector: 'app-genre-page',
  imports: [],
  templateUrl: './genre-page.html',
  styleUrl: './genre-page.css',
})
<<<<<<< Updated upstream
export class GenrePage implements OnInit {
  private genreService: GenreService = inject(GenreService);
}
