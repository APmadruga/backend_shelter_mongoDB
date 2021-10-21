package com.pet.manager.service;

import com.pet.manager.model.Author;
import com.pet.manager.repository.AuthorRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getByFirstName(String firstName) {
        final var optionalAuthor = authorRepository.findAuthorByFirstName(firstName);
        if (optionalAuthor.isPresent()) {
            return optionalAuthor.get();
        }
        throw new IllegalArgumentException("No Author was found");
    }

    public Author createAuthor(String firstName, String lastName, int age) {
        try {
            return authorRepository.insert(Author.builder().age(age).firstName(firstName).lastName(lastName).build());
        } catch (DuplicateKeyException exception) {
            throw new IllegalArgumentException("Duplicated firstName is not allowed");
        }
    }
}
