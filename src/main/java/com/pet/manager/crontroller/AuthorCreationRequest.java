package com.pet.manager.crontroller;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorCreationRequest {
    private String firstName;
    private String lastName;
    private int age;
}
