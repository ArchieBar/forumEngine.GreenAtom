package ru.greenatom.model.topic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Topic {
    @Id
    private String id;
    private String name;
    private LocalDateTime created;

    public Topic(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.created = LocalDateTime.now();
    }
}
