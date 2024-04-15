package ru.greenatom.exception.topic;

public class TopicDuplicateException extends RuntimeException {
    public TopicDuplicateException(String message) {
        super(message);
    }
}
