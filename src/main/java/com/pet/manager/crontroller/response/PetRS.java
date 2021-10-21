package com.pet.manager.crontroller.response;
import com.pet.manager.model.PetType;
import lombok.*;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetRS {
    private int age;
    private String firstName;
    private PetType petType;
}
