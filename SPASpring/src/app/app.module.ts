import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSidenavModule } from '@angular/material/sidenav'
import { FormsModule, ReactiveFormsModule} from '@angular/forms'
import { MatMenuModule} from '@angular/material/menu'
import { MatInputModule} from '@angular/material/input'
import { MatDatepickerModule} from '@angular/material/datepicker'
import { MatTableModule} from '@angular/material/table'
import { MatToolbarModule} from '@angular/material/toolbar'
import { MatIconModule} from '@angular/material/icon'
import { MatListModule} from '@angular/material/list'
import { MatButtonModule} from '@angular/material/button'

import { from } from 'rxjs';
import { EditorialsListComponent } from './editorials-list/editorials-list.component';
import { EditorialComponent } from './editorial/editorial.component';
import { AuthorsListComponent } from './authors-list/authors-list.component';
import { AuthorComponent } from './author/author.component';
import { BookComponent } from './book/book.component';

@NgModule({
  declarations: [
    AppComponent,
    EditorialsListComponent,
    EditorialComponent,
    AuthorsListComponent,
    AuthorComponent,
    BookComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSidenavModule,
    FormsModule,
    ReactiveFormsModule,
    MatMenuModule,
    MatInputModule,
    MatDatepickerModule,
    MatTableModule,
    MatToolbarModule,
    MatIconModule,
    MatListModule,
    HttpClientModule,
    MatButtonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
