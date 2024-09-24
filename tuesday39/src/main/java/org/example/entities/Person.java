package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Person {
    @Id
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public Person() {
    }
}
