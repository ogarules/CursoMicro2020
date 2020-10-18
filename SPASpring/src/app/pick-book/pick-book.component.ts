import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { fromEvent } from 'rxjs';
import { BookService } from '../book.service';
import { NotificationService } from '../notification.service';
import { debounceTime, distinctUntilChanged, tap } from 'rxjs/operators';

@Component({
  selector: 'app-pick-book',
  templateUrl: './pick-book.component.html',
  styleUrls: ['./pick-book.component.scss']
})
export class PickBookComponent implements OnInit, AfterViewInit {

  textFilter : String = '';
  bookList : any[] = [];
  @ViewChild('searchInput', {static: false}) input: ElementRef;
  constructor(private bookService : BookService, private notificationService : NotificationService, private dialogRef : MatDialogRef<PickBookComponent>) { }

  ngOnInit(): void {
    this.bookService.filterBooks('').subscribe((data) =>{
      this.bookList = data;
    }, error =>{
      this.notificationService.showError(error);
    });
  }

  ngAfterViewInit() {  
    fromEvent(this.input.nativeElement,'keyup')
                    .pipe(
                        debounceTime(950),
                        distinctUntilChanged(),
                        tap(() => {
                          this.filter();
                        })
                    )
                    .subscribe();
  }

  filter(){
    this.bookService.filterBooks('?title='+this.textFilter).subscribe((data) =>{
      this.bookList = data;
    }, error =>{
      this.notificationService.showError(error);
    });
  }

  selectBook(book){
    this.dialogRef.close(book);
  }

  cancelDialog(){
    this.dialogRef.close();
  }
}
