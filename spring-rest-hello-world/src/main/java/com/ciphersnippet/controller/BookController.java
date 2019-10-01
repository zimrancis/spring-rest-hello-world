package com.ciphersnippet.controller;

/*import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
*/import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.ciphersnippet.exceptionhandler.BookNotFoundException;
import com.ciphersnippet.exceptionhandler.BookUnSupportedFieldPatchException;
import com.ciphersnippet.model.Book;
import com.ciphersnippet.repository.BookRepository;

import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    private BookRepository repository;

    // Find
    @GetMapping("/books")
    public List<Book> findAll() {
        return repository.findAll();
    }

    // Save
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/books")
    public Book newBook(@RequestBody Book newBook) {
        return repository.save(newBook);
    }

    // Find
    @GetMapping("/books/{id}")
    public Book findOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    // Save or update
    @PutMapping("/books/{id}")
    public Book saveOrUpdate(@RequestBody Book newBook, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {
                    x.setName(newBook.getName());
                    x.setAuthor(newBook.getAuthor());
                    x.setPrice(newBook.getPrice());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return repository.save(newBook);
                });
    }

    // update author only
    @PatchMapping("/books/{id}")
    public Book patch(@RequestBody Map<String, String> update, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {

                    String author = update.get("author");
                    if (!StringUtils.isEmpty(author)) {
                        x.setAuthor(author);

                        // better create a custom method to update a value = :newValue where id = :id
                        return repository.save(x);
                    } else {
                        throw new BookUnSupportedFieldPatchException(update.keySet());
                    }

                })
                .orElseGet(() -> {
                    throw new BookNotFoundException(id);
                });

    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
    }
    
    /*Configuration con = new Configuration().configure().addAnnotatedClass(Book.class);
    ServiceRegistry reg= new StandardServiceRegistryBuilder().applySettings(con.getProperties()).getBootstrapServiceRegistry();
    SessionFactory sf = con.buildSessionFactory(reg);
    Session session = sf.openSession();
    Transaction tx = session.beginTransaction();
    Session.save(book);
    tx.commit();*/

}
