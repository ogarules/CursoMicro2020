import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from '../book.service';
import { NotificationService } from '../notification.service';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.scss']
})
export class BookComponent implements OnInit {

  book : any = {};
  sourceId : number = 0;
  mode : String = '';

  constructor(private route : ActivatedRoute, private router : Router, private bookService : BookService, private notificationService : NotificationService) { }

  ngOnInit(): void {
    this.sourceId = this.route.snapshot.params.sourceid;
    this.book.id = this.route.snapshot.params.id;

    this.route.data.subscribe(v => {
      if(v.source){
        this.mode = v.source;
      }

      if(this.book.id > 0){
        this.loadBook();
      }
    });
  }

  loadBook(){
    if(this.mode == 'author'){
      this.bookService.getBookFromAuthor(this.sourceId, this.book.id).subscribe((data) =>{
        this.book = data;
      }, error =>{
        this.notificationService.showError(error);
      });
    }
    else {
      this.bookService.getBookFromEditorial(this.sourceId, this.book.id).subscribe((data) =>{
        this.book = data;
      }, error =>{
        this.notificationService.showError(error);
      });
    }
  }

  save(){
    if(this.book.id >0){
      this.updateBook();
    }
    else{
      this.addBook();
    }
  }

  addBook(){
    if(this.mode == 'author'){
      this.bookService.addBookToAuthor(this.sourceId, this.book).subscribe((data) =>{
        this.notificationService.showInformation('El libro se agrego correctamente al autor');
        this.router.navigate(['../'], { relativeTo: this.route});
      }, error =>{
        this.notificationService.showError(error);
      });
    }
    else {
      this.bookService.addBookToEditorial(this.sourceId, this.book).subscribe((data) =>{
        this.notificationService.showInformation('El libro se agrego correctamente a la editorial');
        this.router.navigate(['../'], { relativeTo: this.route});
      }, error =>{
        this.notificationService.showError(error);
      });
    }
  }

  updateBook(){
    if(this.mode == 'author'){
      this.bookService.updateBookFromAuthor(this.sourceId, this.book).subscribe((data) =>{
        this.notificationService.showInformation('El libro se actualizo correctamente al autor');
        this.router.navigate(['../'], { relativeTo: this.route});
      }, error =>{
        this.notificationService.showError(error);
      });
    }
    else {
      this.bookService.updateBookFromEditorial(this.sourceId, this.book).subscribe((data) =>{
        this.notificationService.showInformation('El libro se actualizo correctamente a la editorial');
        this.router.navigate(['../'], { relativeTo: this.route});
      }, error =>{
        this.notificationService.showError(error);
      });
    }
  }
}
