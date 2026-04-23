import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EditeurResponse } from '../../model/editeurResponse';

@Injectable({
  providedIn: 'root',
})
export class EditeurService {
  constructor(private httpClient: HttpClient){}

  public findAll():Observable<EditeurResponse[]>{
    return this.httpClient.get<EditeurResponse[]>("/editeur")
  }
}
