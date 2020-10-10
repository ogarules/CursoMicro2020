import { Component, OnInit } from '@angular/core';
import { AuthorService } from '../author.service';
import { NotificationService } from '../notification.service';

@Component({
  selector: 'app-authors-list',
  templateUrl: './authors-list.component.html',
  styleUrls: ['./authors-list.component.scss']
})
export class AuthorsListComponent implements OnInit {

  authorsList : any[] = [];

  constructor(private authorsService : AuthorService, private notificationService : NotificationService) { }

  ngOnInit(): void {
    this.authorsService.getAllAuthors().subscribe((data : any[]) =>{
      this.authorsList = data;
    }, error =>{
      this.notificationService.showError(error);
    });
  }

  editAuthor(author){

  }

  addAuthor(){
    
  }
}
