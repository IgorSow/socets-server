package chat.infrastructure;

import chat.domain.model.ChatUser;
import chat.domain.port.UsersRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUsersRepository implements UsersRepository {

    private List<ChatUser> users;


    public InMemoryUsersRepository(){

        this.users = new ArrayList<>();
    }
    public ChatUser addUser(ChatUser user){
        users.add(user);
        return null;
    }

    @Override
    public ChatUser find(String address) {
        return null;
    }

    @Override
    public List<ChatUser> findAll() {
        return null;
    }


}
