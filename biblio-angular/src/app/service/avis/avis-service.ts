import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Avis } from '../../model/avis';

@Injectable({
  providedIn: 'root',
})
export class AvisService {
  protected avis$!: Observable<Avis[]>;

  constructor(private http: HttpClient) {}

  public findAll(): Observable<Avis[]> {
    return this.http.get<Avis[]>("/avis");
  }

  public add(newAvis: Avis): Observable<Avis> {
    return this.http.post<Avis>("/avis", newAvis);
  }

  public update(updatedAvis: Avis): Observable<Avis> {
    return this.http.put<Avis>(`/avis/${updatedAvis.id}`,updatedAvis);
  }

  public delete(id: number): Observable<Avis> {
    return this.http.delete<Avis>(`/avis/${id}`);
  }

}
