import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { BookService } from '../book.service';
import { NotificationService } from '../notification.service';
import { PickBookComponent } from '../pick-book/pick-book.component';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.scss']
})
export class BookListComponent implements OnInit {

  sourceId : number = 0;
  mode : String = '';
  bookList : any[] = [];
  constructor(private route : ActivatedRoute, private bookService : BookService, private notificationService : NotificationService, private dialog : MatDialog) { }

  ngOnInit(): void {
    if(this.route.snapshot.params.id){
      this.sourceId = this.route.snapshot.params.id;
    }

    this.route.data.subscribe(v => {
      if(v.source){
        this.mode = v.source;
      }

      this.listBooks();
    });
  }

  listBooks(){
    if(this.mode == 'author'){
      this.bookService.getAllBooksFromAuthor(this.sourceId).subscribe((data) => {
        this.bookList = data;
      }, error =>{
        this.notificationService.showError(error);
      });
    }
    else {
      this.bookService.getAllBooksFromEditorial(this.sourceId).subscribe((data) => {
        this.bookList = data;
      }, error =>{
        this.notificationService.showError(error);
      });
    }
  }

  addBookToEditorial(){
    const dialogRef = this.dialog.open(PickBookComponent, {
      width: '650px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.bookService.addBookToEditorial(this.sourceId, result).subscribe((data) =>{
          this.notificationService.showInformation('El libro ha sido asignado a la editorial');
          this.listBooks();
        }, error =>{
          this.notificationService.showError(error);
        });
      }
    });
  }
}
