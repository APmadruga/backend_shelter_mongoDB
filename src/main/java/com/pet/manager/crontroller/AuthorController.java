package com.pet.manager.crontroller;


import com.pet.manager.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public ResponseEntity<AuthorResponse> getAuthorByFirstName(@RequestParam String firstName) {
        final var author = authorService.getByFirstName(firstName);
        final var response = AuthorResponse.builder()
                .age(author.getAge())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/authors")
    public ResponseEntity<AuthorResponse> getAuthorByFirstName(@RequestBody AuthorCreationRequest request) {

        final var author = authorService.createAuthor(request.getFirstName(), request.getLastName(), request.getAge());

        return ResponseEntity.created(URI.create("/authors/" + author.getId())).build();
    }
}
