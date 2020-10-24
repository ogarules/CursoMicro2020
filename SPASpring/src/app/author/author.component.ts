import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { AuthorService } from '../author.service';
import { NotificationService } from '../notification.service';

@Component({
  selector: 'app-author',
  templateUrl: './author.component.html',
  styleUrls: ['./author.component.scss']
})
export class AuthorComponent implements OnInit {

  pictureUrl : String = '';
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
        this.pictureUrl = `${environment.apiUrl}/author/${this.author.id}/image/${this.author.picture}`;
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

  onFileSelect(event){
    let paramItem = event.target;
    if(paramItem){
      let fileNameItem : String = paramItem.files[0].name.toLowerCase();

      if(!fileNameItem.endsWith(".jpg") && !fileNameItem.endsWith(".jpeg")){
        this.notificationService.showError("Solo se pueden subor imagenes JPG");
        paramItem.value = null;

        return;
      }

      this.authorService.uploadPicture(this.author.id, paramItem.files[0]).subscribe((data) =>{
        this.notificationService.showInformation("La imagen se subio correctamente");
      }, error =>{
        this.notificationService.showError(error);
      })
    }
  }
}
