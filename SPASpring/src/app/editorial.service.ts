import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EditorialService {

  constructor(private httpClient : HttpClient) { 
  }

  getAllEditorials(){
    return this.httpClient.get<any[]>(`${environment.apiUrl}/editorial`);
  }

  getEditorial(id){
    return this.httpClient.get<any>(`${environment.apiUrl}/editorial/${id}`);
  }

  addEditorial(author){
    return this.httpClient.post<any>(`${environment.apiUrl}/editorial`, author);
  }

  updateditorial(author){
    return this.httpClient.put<any>(`${environment.apiUrl}/editorial/${author.id}`, author);
  }
}
