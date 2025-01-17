package ru.greenatom.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.greenatom.model.message.Message;
import ru.greenatom.model.topic.TopicWithMessage;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<TopicWithMessage, String> {
    Optional<TopicWithMessage> findByName(String name);
}
