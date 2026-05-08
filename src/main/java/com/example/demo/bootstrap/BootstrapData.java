package com.example.demo.bootstrap;

import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import com.example.demo.domain.Publisher;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author andrew = new Author();
        andrew.setFirstName("Andrew");
        andrew.setLastName("Woon");

        Book b1 = new Book();
        b1.setTitle("Head First Design Patterns");
        b1.setIsbn("123");

        Publisher p1 = new Publisher();
        p1.setPublisherName("Candid Publishing");
        p1.setAddress("8 Jalan Bukit Merah #08-21");
        p1.setCity("Singapore");
        p1.setState("Singapore");
        p1.setZip("S765340");

        Author andrewSaved = authorRepository.save(andrew);
        Book b1Saved = bookRepository.save(b1);
        Publisher p1Saved = publisherRepository.save(p1);

        /* Need to add the data to the owning side or else nothing gets written to the junction table */
        b1Saved.getAuthors().add(andrewSaved);

        b1Saved.setPublisher(p1Saved);

        bookRepository.save(b1Saved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());


    }
}
