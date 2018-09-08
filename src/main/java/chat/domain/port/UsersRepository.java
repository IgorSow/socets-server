package chat.domain.port;

import chat.domain.model.ChatUser;

import java.util.List;

public interface UsersRepository {

    ChatUser addUser(ChatUser user);

    ChatUser find(String address);

    List<ChatUser> findAll();
}
