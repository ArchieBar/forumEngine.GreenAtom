package ru.greenatom.model.topic;

import lombok.*;
import ru.greenatom.exception.message.MessageNotFoundException;
import ru.greenatom.model.message.Message;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TopicWithMessage extends Topic {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Message> messages;

    public TopicWithMessage(String name, List<Message> messages) {
        super(name);
        this.messages = messages;
    }

    public TopicWithMessage(String name) {
        super(name);
        this.messages = new ArrayList<>();
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void removeMessage(Message message) {
        if (!messages.remove(message)) {
            throw new MessageNotFoundException(MessageFormat.format(
                    "Сообщение с ID: {0} не найдено в теме c ID: {1}.", message.getId(), super.getId()
            ));
        }

    }
}

