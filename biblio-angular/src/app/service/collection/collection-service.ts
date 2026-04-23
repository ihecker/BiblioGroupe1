import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Collection } from '../../model/collection';

@Injectable({
  providedIn: 'root',
})
export class CollectionService {
  // outil qui permet d'appeler le backend
  constructor(private http: HttpClient) {}
  
  // Observable -> on va avoir la liste de collection mais pas tt de suite
  public findAll(): Observable<Collection[]>
  {
    return this.http.get<Collection[]>("/collection");
  }

  public add(collection: Collection): Observable<Collection> 
  {
    return this.http.post<Collection>("/collection", collection);
  }
  
  public deleteById(id: number): Observable<void> 
  {
    return this.http.delete<void>(`/collection/${ id }`);
  }

  public update(id: number, collection: Collection): Observable<Collection>
  {
    return this.http.put<Collection>(`/collection/${ id }`, collection);
  }
}
