package ru.greenatom.model.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.greenatom.model.user.UserDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private String text;
    private String author;
}
