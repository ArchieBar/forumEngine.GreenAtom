package ru.greenatom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.greenatom.exception.topic.TopicNotFoundException;
import ru.greenatom.model.message.Message;
import ru.greenatom.model.message.MessageDto;
import ru.greenatom.model.topic.*;
import ru.greenatom.repository.TopicRepository;

import java.text.MessageFormat;
import java.util.UUID;

@Service
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;
    private final MessageService messageService;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository, MessageService messageService) {
        this.topicRepository = topicRepository;
        this.messageService = messageService;
    }

    @Override
    public TopicWithMessage createTopic(TopicWithMessageDto newTopic) {
        TopicWithMessage topicWithMessage = new TopicWithMessage(
                newTopic.getTopicName()
        );
        Message message = messageService.createMessage(newTopic.getMessage(),
                UUID.fromString(topicWithMessage.getId()));
        topicWithMessage.addMessage(message);
        return topicRepository.saveAndFlush(topicWithMessage);
    }

    @Override
    public TopicWithMessage updateTopic(UUID topicId, TopicDto updateTopic) {
        TopicWithMessage topicWithMessage = topicRepository.findById(topicId.toString())
                .orElseThrow(() -> new TopicNotFoundException(MessageFormat.format(
                        "Тема с ID: {0} не найдена", topicId
                )));
        topicWithMessage.setName(updateTopic.getName());
        return topicRepository.saveAndFlush(topicWithMessage);
    }

    @Override
    public Page<Topic> listAllTopics(Pageable pageable) {
        return topicRepository.findAll(pageable)
                .map(TopicMapper::toTopic);
    }

    @Override
    public TopicPageMessage listTopicMessage(UUID topicId, Pageable pageable) {
        Topic topic = TopicMapper.toTopic(topicRepository.findById(topicId.toString())
                .orElseThrow(() -> new TopicNotFoundException(MessageFormat.format(
                        "Тема с ID: {0} не найдена", topicId
                ))));
        Page<Message> messages = messageService.findByTopicId(topicId, pageable);
        return new TopicPageMessage(topic, messages);
    }

    @Override
    public TopicWithMessage createMessage(UUID topicId, MessageDto messageDto) {
        TopicWithMessage topicWithMessage = topicRepository.findById(topicId.toString())
                .orElseThrow(() -> new TopicNotFoundException(MessageFormat.format(
                        "Тема с ID: {0} не найдена", topicId
                )));
        topicWithMessage.addMessage(messageService.createMessage(messageDto, topicId));
        return topicRepository.saveAndFlush(topicWithMessage);
    }

    @Override
    public TopicWithMessage updateMessage(UUID topicId,
                                          MessageDto messageDto,
                                          UUID messageId) {
        messageService.updateMessage(messageId, messageDto);
        return topicRepository.findById(topicId.toString())
                .orElseThrow(() -> new TopicNotFoundException(MessageFormat.format(
                        "Тема с ID: {0} не найдена", topicId
                )));
    }

    @Override
    public void deleteMessage(UUID topicId, UUID messageId) {
        TopicWithMessage topicWithMessage = topicRepository.findById(topicId.toString())
                .orElseThrow(() -> new TopicNotFoundException(MessageFormat.format(
                        "Тема с ID: {0} не найдена", topicId
                )));
        topicWithMessage.removeMessage(messageService.findById(messageId));
        messageService.deleteMessage(messageId);
        if (topicWithMessage.getMessages().isEmpty()) {
            topicRepository.deleteById(topicId.toString());
        }
    }
}
