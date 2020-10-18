import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthorService } from '../author.service';
import { NotificationService } from '../notification.service';

@Component({
  selector: 'app-author',
  templateUrl: './author.component.html',
  styleUrls: ['./author.component.scss']
})
export class AuthorComponent implements OnInit {

  author : any = {};
  constructor(private router: Router, private route : ActivatedRoute, private authorService : AuthorService, private notificationService : NotificationService) { 
    if(route.snapshot.params.id){
      this.author.id = route.snapshot.params.id;
    }
  }

  ngOnInit(): void {
    if(this.author.id > 0){
      this.authorService.getAuthor(this.author.id).subscribe((data) =>{
        this.author = data;
      }, error =>{
        this.notificationService.showError(error);
      });
    }
  }

  save(){
    if(this.author.id > 0){
      this.authorService.updateAuthor(this.author).subscribe((data) => {
        this.notificationService.showInformation('El autor fue actualizado exitosamente');
        this.router.navigate(['/authors-list']);
      }, error =>{
        this.notificationService.showError(error);
      });
    }    
    else{
      this.authorService.addAuthor(this.author).subscribe((data) => {
        this.notificationService.showInformation('El autor fue agregado exitosamente');
        this.router.navigate(['/authors-list']);
      }, error =>{
        this.notificationService.showError(error);
      });
    }
  }
}
