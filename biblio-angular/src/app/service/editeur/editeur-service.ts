import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EditeurResponse } from '../../model/editeur-response';
import { EditeurRequest } from '../../model/editeur-request';


@Injectable({
  providedIn: 'root',
})
export class EditeurService {
  constructor(private httpClient: HttpClient){}

  public findAll():Observable<EditeurResponse[]>{
    return this.httpClient.get<EditeurResponse[]>("/editeur")
  }

  public addEditeur(editeur:EditeurRequest):Observable<EditeurResponse>{
    return this.httpClient.post<EditeurResponse>("/editeur",editeur);
  }

  public patchEditeur(editeur:EditeurRequest,id:number):Observable<EditeurResponse>{
    return this.httpClient.put<EditeurResponse>("/editeur/"+id,editeur);
  }

  public deleteEditeur(id:number):Observable<void>{
    return this.httpClient.delete<void>("/editeur/"+id)
  }
}
