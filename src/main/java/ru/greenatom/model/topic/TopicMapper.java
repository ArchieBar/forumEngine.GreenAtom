package ru.greenatom.model.topic;

import java.util.List;
import java.util.stream.Collectors;

public class TopicMapper {
    public static Topic toTopic(TopicWithMessage topicWithMessage) {
        return new Topic(
                topicWithMessage.getId(),
                topicWithMessage.getName(),
                topicWithMessage.getCreated()
        );
    }

    public static List<Topic> toTopicList(List<TopicWithMessage> topicWithMessageList) {
        return topicWithMessageList.stream()
                .map(TopicMapper::toTopic)
                .collect(Collectors.toList());
    }
}
