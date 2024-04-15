package ru.greenatom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.greenatom.exception.message.MessageNotFoundException;
import ru.greenatom.model.message.Message;
import ru.greenatom.model.message.MessageDto;
import ru.greenatom.repository.MessageRepository;

import java.text.MessageFormat;
import java.util.UUID;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message createMessage(MessageDto messageDto, UUID topicId) {
        Message message = new Message(
                topicId,
                messageDto.getText(),
                messageDto.getAuthor()
        );
        return messageRepository.saveAndFlush(message);
    }

    @Override
    public Message updateMessage(UUID messageId, MessageDto messageDto) {
        Message message = findById(messageId);
        if (messageDto.getText() != null) {
            message.setText(messageDto.getText());
        }
        if (messageDto.getAuthor() != null) {
            message.setAuthor(messageDto.getAuthor());
        }
        return messageRepository.saveAndFlush(message);
    }

    @Override
    public void deleteMessage(UUID messageId) {
        messageRepository.deleteById(messageId.toString());
    }

    @Override
    public Message findById(UUID messageId) {
        return messageRepository.findById(messageId.toString())
                .orElseThrow(() -> new MessageNotFoundException(MessageFormat.format(
                        "Сообщение с ID: {0} не найдено.", messageId
                )));
    }

    @Override
    public Page<Message> findByTopicId(UUID topicId, Pageable pageable) {
        return messageRepository.findByTopicId(topicId.toString(), pageable);
    }
}
