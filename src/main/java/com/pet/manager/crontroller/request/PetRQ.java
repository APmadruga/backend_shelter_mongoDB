package com.pet.manager.crontroller.request;
import com.pet.manager.model.Feed;
import com.pet.manager.model.PetType;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetRQ {
    private int age;
    private String firstName;
    private PetType petType;
    private List<Feed> feed;
}
