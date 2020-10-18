import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EditorialService } from '../editorial.service';
import { NotificationService } from '../notification.service';

@Component({
  selector: 'app-editorial',
  templateUrl: './editorial.component.html',
  styleUrls: ['./editorial.component.scss']
})
export class EditorialComponent implements OnInit {

  editorial : any = {};
  constructor(private route : ActivatedRoute, private router : Router, private editorialService : EditorialService, private notificationService : NotificationService) { }

  ngOnInit(): void {
    this.editorial.id = this.route.snapshot.params.id;

    if(this.editorial.id > 0){
      this.editorialService.getEditorial(this.editorial.id).subscribe((data) =>{
        this.editorial = data;
      }, error => {
        this.notificationService.showError(error);
      });
    }
  }

  save(){
    if(this.editorial.id > 0){
      this.editorialService.updateditorial(this.editorial).subscribe((data) =>{
        this.notificationService.showInformation('La editorial se actualizo correctamente');
        this.router.navigate(['editorials-list']);
      }, error => {
        this.notificationService.showError(error);
      });
    }
    else
    {
      this.editorialService.addEditorial(this.editorial).subscribe((data) =>{
        this.notificationService.showInformation('La editorial se inserto correctamente');
        this.router.navigate(['editorials-list']);
      }, error => {
        this.notificationService.showError(error);
      });
    }
  }
}
