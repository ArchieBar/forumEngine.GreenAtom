package ru.greenatom.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private UUID id;
    private String nickname;

    public User(String nickname) {
        this.id = UUID.randomUUID();
        this.nickname = nickname;
    }
}
