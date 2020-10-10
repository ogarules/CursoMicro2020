import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private httpClient : HttpClient) { 
  }

  getAllBooksFromAuthor(authorId) {
    return this.httpClient.get<any[]>(`${environment.apiUrl}/author/${authorId}/book`);
  }

  getBookFromAuthor(authorId, bookId){
    return this.httpClient.get<any>(`${environment.apiUrl}/author/${authorId}/book/${bookId}`);
  }

  addBookToAuthor(authorId, book){
    return this.httpClient.post<any>(`${environment.apiUrl}/author/${authorId}/book`, book);
  }

  updateBookFromAuthor(authorId, book) : any {
    return this.httpClient.put<any>(`${environment.apiUrl}/author/${authorId}/book`, book);
  }

  getAllBooksFromEditorial(editorialId){
    return this.httpClient.get<any[]>(`${environment.apiUrl}/editorial/${editorialId}/book`);
  }

  getBookFromEditorial(editorialId, bookId){
    return this.httpClient.get<any>(`${environment.apiUrl}/editorial/${editorialId}/book/${bookId}`);
  }

  addBookToEditorial(editorialId, book){
    return this.httpClient.post<any>(`${environment.apiUrl}/editorial/${editorialId}/book`, book);
  }

  updateBookFromEditorial(editorialId, book){
    return this.httpClient.put<any>(`${environment.apiUrl}/editorial/${editorialId}/book`, book);
  }
}
