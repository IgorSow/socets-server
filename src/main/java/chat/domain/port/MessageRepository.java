package chat.domain.port;

import chat.domain.model.ChatMessage;
import chat.domain.model.ChatUser;

import java.util.List;

public interface MessageRepository {

    void saveMassage(ChatMessage massage);

    List<ChatMessage> findMessagesFor(ChatUser chatUser);
}
