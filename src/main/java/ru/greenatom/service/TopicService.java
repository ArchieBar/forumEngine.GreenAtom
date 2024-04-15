package ru.greenatom.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.greenatom.model.message.MessageDto;
import ru.greenatom.model.topic.*;

import java.util.List;
import java.util.UUID;

public interface TopicService {
    TopicWithMessage createTopic(TopicWithMessageDto newTopic);

    TopicWithMessage updateTopic(UUID topicId, TopicDto updateTopic);

    Page<Topic> listAllTopics(Pageable pageable);

    TopicPageMessage listTopicMessage(UUID topicId, Pageable pageable);

    TopicWithMessage createMessage(UUID topicId, MessageDto messageDto);

    TopicWithMessage updateMessage(UUID topicId, MessageDto messageDto, UUID messageId);

    void deleteMessage(UUID topicId, UUID messageId);
}
