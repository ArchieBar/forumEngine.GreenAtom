package ru.greenatom.repository;

import org.apache.tomcat.jni.Procattr;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.greenatom.model.message.Message;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, String> {
    Page<Message> findByTopicId(String topicId, Pageable pageable);
}
