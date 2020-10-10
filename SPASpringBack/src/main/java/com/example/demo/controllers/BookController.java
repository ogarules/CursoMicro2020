package com.example.demo.controllers;

import java.util.List;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.Book;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.EditorialRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    EditorialRepository editorialRepository;

    @Autowired
    BookRepository bookRepository;

    @GetMapping(value="/author/{authorId}/book/{bookId}")
    public Book getBookFromAuthor(@PathVariable Integer authorId, @PathVariable Integer bookId) throws ResourceNotFoundException {
        if(!this.authorRepository.existsById(authorId)){
            throw new ResourceNotFoundException("No existe el author !!!!" + authorId);
        }

        return this.bookRepository.findByIdAndAuthorId(bookId, authorId).orElseThrow(() -> new ResourceNotFoundException("No existe el libro relacionado al autor"));
    }
    
    @PutMapping(value="/author/{authorId}/book/{bookId}")
    public Book updateBookFromAuthor(@PathVariable Integer authorId, @PathVariable Integer bookId, @RequestBody Book book) throws ResourceNotFoundException {
        if(!this.authorRepository.existsById(authorId)){
            throw new ResourceNotFoundException("No existe el author !!!!" + authorId);
        }

        return this.bookRepository.findById(bookId).map(bookBd ->{
            bookBd.setPages(book.getPages());
            bookBd.setTitle(book.getTitle());
            bookBd.setPublishDate(book.getPublishDate());
            bookBd.setEdition(book.getEdition());
            bookBd.setType(book.getType());
            return this.bookRepository.save(bookBd);
        }).orElseThrow(() -> new ResourceNotFoundException("No existe el libro relacionado al autor"));
    }

    @PostMapping(value="/author/{authorId}/book")
    public Book addBookToAuthor(@PathVariable Integer authorId, @RequestBody Book book)  throws ResourceNotFoundException {
        return this.authorRepository.findById(authorId).map(author ->{
            book.setAuthor(author);
            return this.bookRepository.save(book);
        }).orElseThrow(() -> new ResourceNotFoundException("No existe el autor weee!!! :" + authorId.toString()));
    }
    
    @GetMapping(value="/author/{authorId}/book")
    public List<Book> getBooksFromAuthor(@PathVariable Integer authorId){
        return this.bookRepository.findByAuthorId(authorId);
    }

    @GetMapping(value="/editorial/{editorialId}/book/{bookId}")
    public Book getBookFromditorial(@PathVariable Integer editorialId, @PathVariable Integer bookId) throws ResourceNotFoundException {
        if(!this.editorialRepository.existsById(editorialId)){
            throw new ResourceNotFoundException("No existe la ditorialr !!!!" + editorialId);
        }

        return this.bookRepository.findByIdAndEditorialId(bookId, editorialId).orElseThrow(() -> new ResourceNotFoundException("No existe el libro relacionado a la editorial"));
    }

    @PostMapping(value="/editorial/{editorialId}/book")
    public Book addBookToEditorial(@PathVariable Integer editorialId, @RequestBody Book book) throws ResourceNotFoundException {
        return this.editorialRepository.findById(editorialId).map(editorial ->{
            book.setEditorial(editorial);
            return this.bookRepository.save(book);
        }).orElseThrow(() -> new ResourceNotFoundException("No existe la editorial weee!!! :" + editorialId.toString()));
    }
    
    @GetMapping(value="/editorial/{editorialId}/book")
    public List<Book> getBooksFromEditorial(@PathVariable Integer editorialId){
        return this.bookRepository.findByEditorialId(editorialId);
    }

    @PutMapping(value="/editorial/{editorialId}/book/{bookId}")
    public Book updateBookFromEditorial(@PathVariable Integer editorialId, @PathVariable Integer bookId, @RequestBody Book book) throws ResourceNotFoundException {
        if(!this.editorialRepository.existsById(editorialId)){
            throw new ResourceNotFoundException("No existe la editorial !!!!" + editorialId);
        }

        return this.bookRepository.findById(bookId).map(bookBd ->{
            bookBd.setPages(book.getPages());
            bookBd.setTitle(book.getTitle());
            bookBd.setPublishDate(book.getPublishDate());
            bookBd.setEdition(book.getEdition());
            bookBd.setType(book.getType());
            return this.bookRepository.save(bookBd);
        }).orElseThrow(() -> new ResourceNotFoundException("No existe el libro relacionado al autor"));
    }
}
