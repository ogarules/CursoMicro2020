import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  constructor(private httpClient : HttpClient) { 
  }

  getAllAuthors(){
    return this.httpClient.get<any[]>(`${environment.apiUrl}/author`);
  }

  getAuthor(id){
    return this.httpClient.get<any>(`${environment.apiUrl}/author/${id}`);
  }

  addAuthor(author){
    return this.httpClient.post<any>(`${environment.apiUrl}/author`, author);
  }

  updateAuthor(author){
    return this.httpClient.put<any>(`${environment.apiUrl}/author/${author.id}`, author);
  }
}
