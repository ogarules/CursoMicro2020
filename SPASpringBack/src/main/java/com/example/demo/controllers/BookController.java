package com.example.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.Book;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.EditorialRepository;
import com.example.demo.to.BookTO;
import com.google.common.collect.Lists;
import com.querydsl.core.types.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
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

    @GetMapping(value="/book")
    public List<BookTO> filterBooks(@QuerydslPredicate(root=Book.class) Predicate predicate) {
        return Lists.newArrayList(this.bookRepository.findAll(predicate)).stream()
        .map(r -> new BookTO(r.getId(), r.getTitle(), r.getPages(), r.getPublishDate(), r.getEdition(), r.getType()))
        .collect(Collectors.toList());
    }

    @GetMapping(value="/author/{authorId}/book/{bookId}")
    public BookTO getBookFromAuthor(@PathVariable Integer authorId, @PathVariable Integer bookId) throws ResourceNotFoundException {
        if(!this.authorRepository.existsById(authorId)){
            throw new ResourceNotFoundException("No existe el author !!!!" + authorId);
        }

        Book bookSaved = this.bookRepository.findByIdAndAuthorId(bookId, authorId).orElseThrow(() -> new ResourceNotFoundException("No existe el libro relacionado al autor"));
        return new BookTO(bookSaved.getId(), bookSaved.getTitle(), bookSaved.getPages(), bookSaved.getPublishDate(), bookSaved.getEdition(), bookSaved.getType());
    }
    
    @PutMapping(value="/author/{authorId}/book/{bookId}")
    public BookTO updateBookFromAuthor(@PathVariable Integer authorId, @PathVariable Integer bookId, @RequestBody Book book) throws ResourceNotFoundException {
        if(!this.authorRepository.existsById(authorId)){
            throw new ResourceNotFoundException("No existe el author !!!!" + authorId);
        }

        Book bookSaved = this.bookRepository.findById(bookId).map(bookBd ->{
            bookBd.setPages(book.getPages());
            bookBd.setTitle(book.getTitle());
            bookBd.setPublishDate(book.getPublishDate());
            bookBd.setEdition(book.getEdition());
            bookBd.setType(book.getType());
            return this.bookRepository.save(bookBd);
        }).orElseThrow(() -> new ResourceNotFoundException("No existe el libro relacionado al autor"));

        return new BookTO(bookSaved.getId(), bookSaved.getTitle(), bookSaved.getPages(), bookSaved.getPublishDate(), bookSaved.getEdition(), bookSaved.getType());
    }

    @PostMapping(value="/author/{authorId}/book")
    public BookTO addBookToAuthor(@PathVariable Integer authorId, @RequestBody Book book)  throws ResourceNotFoundException {
        Book bookSaved = this.authorRepository.findById(authorId).map(author ->{
            book.setAuthor(author);
            return this.bookRepository.save(book);
        }).orElseThrow(() -> new ResourceNotFoundException("No existe el autor weee!!! :" + authorId.toString()));

        return new BookTO(bookSaved.getId(), bookSaved.getTitle(), bookSaved.getPages(), bookSaved.getPublishDate(), bookSaved.getEdition(), bookSaved.getType());
    }
    
    @GetMapping(value="/author/{authorId}/book")
    public List<BookTO> getBooksFromAuthor(@PathVariable Integer authorId){
        return this.bookRepository.findByAuthorId(authorId).stream()
                                                           .map(r -> new BookTO(r.getId(), r.getTitle(), r.getPages(), r.getPublishDate(), r.getEdition(), r.getType()))
                                                           .collect(Collectors.toList());
    }

    @GetMapping(value="/editorial/{editorialId}/book/{bookId}")
    public BookTO getBookFromditorial(@PathVariable Integer editorialId, @PathVariable Integer bookId) throws ResourceNotFoundException {
        if(!this.editorialRepository.existsById(editorialId)){
            throw new ResourceNotFoundException("No existe la ditorialr !!!!" + editorialId);
        }

        Book bookSaved = this.bookRepository.findByIdAndEditorialId(bookId, editorialId).orElseThrow(() -> new ResourceNotFoundException("No existe el libro relacionado a la editorial"));
        return new BookTO(bookSaved.getId(), bookSaved.getTitle(), bookSaved.getPages(), bookSaved.getPublishDate(), bookSaved.getEdition(), bookSaved.getType());
    }

    @PostMapping(value="/editorial/{editorialId}/book")
    public BookTO addBookToEditorial(@PathVariable Integer editorialId, @RequestBody Book book) throws ResourceNotFoundException {
        Book bookSaved = this.editorialRepository.findById(editorialId).map(editorial ->{
            Book bookBd = this.bookRepository.findById(book.getId()).get();
            bookBd.setEditorial(editorial);
            return this.bookRepository.save(bookBd);
        }).orElseThrow(() -> new ResourceNotFoundException("No existe la editorial weee!!! :" + editorialId.toString()));

        return new BookTO(bookSaved.getId(), bookSaved.getTitle(), bookSaved.getPages(), bookSaved.getPublishDate(), bookSaved.getEdition(), bookSaved.getType());
    }
    
    @GetMapping(value="/editorial/{editorialId}/book")
    public List<BookTO> getBooksFromEditorial(@PathVariable Integer editorialId){
        return this.bookRepository.findByEditorialId(editorialId).stream()
        .map(r -> new BookTO(r.getId(), r.getTitle(), r.getPages(), r.getPublishDate(), r.getEdition(), r.getType()))
        .collect(Collectors.toList());
    }

    @PutMapping(value="/editorial/{editorialId}/book/{bookId}")
    public BookTO updateBookFromEditorial(@PathVariable Integer editorialId, @PathVariable Integer bookId, @RequestBody Book book) throws ResourceNotFoundException {
        if(!this.editorialRepository.existsById(editorialId)){
            throw new ResourceNotFoundException("No existe la editorial !!!!" + editorialId);
        }

        Book bookSaved = this.bookRepository.findById(bookId).map(bookBd ->{
            bookBd.setPages(book.getPages());
            bookBd.setTitle(book.getTitle());
            bookBd.setPublishDate(book.getPublishDate());
            bookBd.setEdition(book.getEdition());
            bookBd.setType(book.getType());
            return this.bookRepository.save(bookBd);
        }).orElseThrow(() -> new ResourceNotFoundException("No existe el libro relacionado al autor"));

        return new BookTO(bookSaved.getId(), bookSaved.getTitle(), bookSaved.getPages(), bookSaved.getPublishDate(), bookSaved.getEdition(), bookSaved.getType());
    }
}
