package chat.domain.port;

import chat.domain.model.ChatUser;

import java.util.List;

public interface UsersRepository {

    ChatUser addUser(ChatUser user);

    ChatUser findByAddress(String address);

    ChatUser findByName(String receiveName);

    List<ChatUser> findAll();

}
