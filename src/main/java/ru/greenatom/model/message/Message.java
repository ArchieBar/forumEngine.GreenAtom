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
    private String id;
    private String topicId;
    private String text;
    private String author;
    private LocalDateTime created;

    public Message(UUID topicId, String text, String author) {
        this.id = UUID.randomUUID().toString();
        this.topicId = topicId.toString();
        this.text = text;
        this.author = author;
        this.created = LocalDateTime.now();
    }
}
