import { Component } from '@angular/core';
import { OktaAuthService } from '@okta/okta-angular';
import { OnInit } from '@angular/core'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'spaspring';

  isAuthenticated : boolean = false;

  constructor(public authService : OktaAuthService){
  }

  async ngOnInit() {
    this.isAuthenticated = await this.authService.isAuthenticated();
    this.authService.$authenticationState.subscribe((isAuthenticated : boolean ) => {
      this.isAuthenticated = isAuthenticated;
    });
  }
}
