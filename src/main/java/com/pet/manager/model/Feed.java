package com.pet.manager.model;
import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Feed {
    @Id
    private String id;
    private int feedAmount;
}
