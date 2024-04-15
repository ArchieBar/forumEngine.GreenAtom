package ru.greenatom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.greenatom.exception.topic.TopicNotFoundException;
import ru.greenatom.model.message.Message;
import ru.greenatom.model.message.MessageDto;
import ru.greenatom.model.topic.*;
import ru.greenatom.repository.TopicRepository;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
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

    //TODO
    // Вместо проверки на дубликат темы, добавить ограничение в бд на уникальность значения
    @Override
    public TopicWithMessage createTopic(TopicWithMessageDto newTopic) {
        List<Message> messages = new ArrayList<>();
        messages.add(messageService.createMessage(newTopic.getMessage()));
        TopicWithMessage topicWithMessage = new TopicWithMessage(
                newTopic.getTopicName(),
                messages
        );
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
    public List<Topic> listAllTopics() {
        return TopicMapper.toTopicList(topicRepository.findAll());
    }

    @Override
    public TopicWithMessage listTopicMessage(UUID topicId) {
        return topicRepository.findById(topicId.toString())
                .orElseThrow(() -> new TopicNotFoundException(MessageFormat.format(
                        "Тема с ID: {0} не найдена", topicId
                )));
    }

    @Override
    public TopicWithMessage createMessage(UUID topicId, MessageDto messageDto) {
        TopicWithMessage topicWithMessage = topicRepository.findById(topicId.toString())
                .orElseThrow(() -> new TopicNotFoundException(MessageFormat.format(
                        "Тема с ID: {0} не найдена", topicId
                )));
        topicWithMessage.addMessage(messageService.createMessage(messageDto));
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
