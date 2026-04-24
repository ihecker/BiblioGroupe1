import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Auteur } from '../model/auteur';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuteurService {

  private apiUrl = '/auteur';

  constructor(private http: HttpClient) {}

  getAll() {
    return this.http.get<any[]>(this.apiUrl);
  }

  add(auteur: any) {
    return this.http.post(this.apiUrl,auteur);
  }

  delete(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

  update(auteur: Auteur) {
    return this.http.put(`${this.apiUrl}/${auteur.id}`, auteur)
  }

  
}
