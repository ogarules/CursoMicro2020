import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor() { }

  showError(error){
    window.alert(error);
  }

  showInformation(message){
    window.alert(message);
  }
}
