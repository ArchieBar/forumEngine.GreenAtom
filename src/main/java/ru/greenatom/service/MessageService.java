package ru.greenatom.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.greenatom.model.message.Message;
import ru.greenatom.model.message.MessageDto;
import ru.greenatom.repository.MessageRepository;

import java.util.UUID;

public interface MessageService {
    Message createMessage(MessageDto messageDto, UUID topicId);

    Message updateMessage(UUID messageId, MessageDto messageDto);

    void deleteMessage(UUID messageId);

    Message findById(UUID messageId);
    Page<Message> findByTopicId(UUID topicId, Pageable pageable);
}
