package ru.greenatom.service;

import ru.greenatom.model.message.Message;
import ru.greenatom.model.message.MessageDto;
import ru.greenatom.repository.MessageRepository;

import java.util.UUID;

public interface MessageService {
    Message createMessage(MessageDto messageDto);

    Message updateMessage(UUID messageId, MessageDto messageDto);

    void deleteMessage(UUID messageId);

    Message findById(UUID messageId);
}
