package ru.greenatom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.greenatom.model.message.Message;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, String> {
}
