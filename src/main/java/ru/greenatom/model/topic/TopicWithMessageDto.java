package ru.greenatom.model.topic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.greenatom.model.message.MessageDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicWithMessageDto {
    private String topicName;
    private MessageDto Message;
}
