import { Component, OnInit } from '@angular/core';
import { EditorialService } from '../editorial.service';
import { NotificationService } from '../notification.service';

@Component({
  selector: 'app-editorials-list',
  templateUrl: './editorials-list.component.html',
  styleUrls: ['./editorials-list.component.scss']
})
export class EditorialsListComponent implements OnInit {

  editorialList : any[] = [];
  constructor(private editorialService : EditorialService, private notificationService : NotificationService) { }

  ngOnInit(): void {

    this.editorialService.getAllEditorials().subscribe((data) =>{
      this.editorialList = data;
    }, error =>{
      this.notificationService.showError(error);
    });
  }

}
