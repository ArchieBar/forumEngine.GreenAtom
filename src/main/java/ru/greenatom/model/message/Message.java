package ru.greenatom.model.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.greenatom.model.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Message {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String text;
    private String author;
    private LocalDateTime created;

    public Message(String text, String author) {
        this.id = UUID.randomUUID().toString();
        this.text = text;
        this.author = author;
        this.created = LocalDateTime.now();
    }
}
