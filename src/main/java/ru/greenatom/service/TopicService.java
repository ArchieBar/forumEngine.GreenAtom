package ru.greenatom.service;

import ru.greenatom.model.message.MessageDto;
import ru.greenatom.model.topic.Topic;
import ru.greenatom.model.topic.TopicDto;
import ru.greenatom.model.topic.TopicWithMessage;
import ru.greenatom.model.topic.TopicWithMessageDto;

import java.util.List;
import java.util.UUID;

public interface TopicService {
    TopicWithMessage createTopic(TopicWithMessageDto newTopic);

    TopicWithMessage updateTopic(UUID topicId, TopicDto updateTopic);

    List<Topic> listAllTopics();

    TopicWithMessage listTopicMessage(UUID topicId);

    TopicWithMessage createMessage(UUID topicId, MessageDto messageDto);

    TopicWithMessage updateMessage(UUID topicId, MessageDto messageDto, UUID messageId);

    void deleteMessage(UUID topicId, UUID messageId);
}
