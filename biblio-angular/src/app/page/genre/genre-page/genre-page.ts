import { Component, inject, OnInit } from '@angular/core';
import { GenreService } from '../../../service/genre/genre-service';

@Component({
  selector: 'app-genre-page',
  imports: [],
  templateUrl: './genre-page.html',
  styleUrl: './genre-page.css',
})
export class GenrePage implements OnInit {
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
  private genreService: GenreService = inject(GenreService);
}
