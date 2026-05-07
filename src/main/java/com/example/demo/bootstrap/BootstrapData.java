package com.example.demo.bootstrap;

import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author andrew = new Author();
        andrew.setFirstName("Andrew");
        andrew.setLastName("Woon");

        Book b1 = new Book();
        b1.setTitle("Head First Design Patterns");
        b1.setIsbn("123");

        Author andrewSaved = authorRepository.save(andrew);
        Book b1Saved = bookRepository.save(b1);

        /* Need to add the data to the owning side or else nothing gets written to the junction table */
        b1Saved.getAuthors().add(andrewSaved);
        bookRepository.save(b1Saved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());


    }
}
