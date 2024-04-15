package ru.greenatom.model.topic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import ru.greenatom.model.message.Message;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicPageMessage {
    private Topic topic;
    private Page<Message> messages;
}
