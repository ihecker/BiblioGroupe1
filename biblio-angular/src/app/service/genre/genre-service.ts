import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Genre } from '../../model/genre';

@Injectable({
  providedIn: 'root',
})
export class GenreService {
  constructor(private http: HttpClient) {}

  public findAll(): Observable<Genre[]> {
    return this.http.get<Genre[]>('/genre');
  }

  public findById(id: number): Observable<Genre> {
    return this.http.get<Genre>(`/genre/${id}`);
  }

  public insert(genre: Genre): Observable<Genre> {
    return this.http.post<Genre>('/genre', genre);
  }

  public update(genre: Genre): Observable<Genre> {
    return this.http.put<Genre>('/genre', genre);
  }

  public delete(id: number): Observable<void> {
    return this.http.delete<void>(`/genre/${id}`);
  }
}
