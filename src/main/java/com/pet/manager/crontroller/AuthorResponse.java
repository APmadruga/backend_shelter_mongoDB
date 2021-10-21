package com.pet.manager.crontroller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class AuthorResponse {
    private String firstName;
    private String lastName;
    private int age;
}
