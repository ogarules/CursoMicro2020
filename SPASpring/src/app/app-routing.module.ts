import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthorComponent } from './author/author.component';
import { AuthorsListComponent } from './authors-list/authors-list.component';
import { BookListComponent } from './book-list/book-list.component';
import { BookComponent } from './book/book.component';
import { EditorialComponent } from './editorial/editorial.component';
import { EditorialsListComponent } from './editorials-list/editorials-list.component';

const routes: Routes = [
  {path: 'authors-list', component: AuthorsListComponent},
  {path: 'editorials-list', component: EditorialsListComponent},
  {path: 'author/:id', component: AuthorComponent},
  {path: 'author/:id/book', component: BookListComponent, data : {source: 'author'}},
  {path: 'author/:sourceid/book/:id', component: BookComponent, data : {source: 'author'}},
  {path: 'editorial/:id', component: EditorialComponent},
  {path: 'editorial/:id/book', component: BookListComponent, data : {source: 'editorial'}},
  {path: 'editorial/:sourceid/book/:id', component: BookComponent, data : {source: 'editorial'}},
  {path: 'book/:id', component: BookComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
