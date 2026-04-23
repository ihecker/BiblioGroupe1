import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Livre } from '../model/livre';

@Injectable({
  providedIn: 'root',
})
export class LivreService {

    constructor(private http: HttpClient) { }

  public findAll(): Observable<Livre[]> {
    return this.http.get<Livre[]>("/livres");
  }

  public findById(id: number): Observable<Livre> {
    return this.http.get<Livre>(`/livres/${ id }`);
  }

  public add(livre: Livre): Observable<Livre> {
    return this.http.post<Livre>("/livres", livre);
  }

  public update(livre: Livre): Observable<Livre> {
    return this.http.put<Livre>(`/livres/${ livre.id }`, livre);
  }

  public deleteById(id: number): Observable<void> {
    return this.http.delete<void>(`/livres/${ id }`);
  }

}
