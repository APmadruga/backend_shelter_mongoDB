package com.pet.manager.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pet {
    @Id
    private String id;
    private int age;
    @Indexed(unique = true)
    private String firstName;
    @Indexed(unique = false)
    private PetType petType;
    private List<Feed> feedKG;
}
