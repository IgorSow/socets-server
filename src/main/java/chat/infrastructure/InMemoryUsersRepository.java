package chat.infrastructure;

import chat.domain.model.ChatUser;
import chat.domain.port.UsersRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUsersRepository implements UsersRepository {

    private List<ChatUser> users;


    public InMemoryUsersRepository() {

        this.users = new ArrayList<>();
    }

    public ChatUser addUser(ChatUser user) {
        users.add(user);
        return user;
    }

    @Override
    public ChatUser findByAddress(String address) {

        for (ChatUser user : users) {
            if (address.equalsIgnoreCase(user.getAddress())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public ChatUser findByName(String name) {
        for (ChatUser user : users){
            if ( name.equalsIgnoreCase(user.getName()))
                return user;
        }

        return null;
    }

    @Override
    public List<ChatUser> findAll() {
        return new ArrayList<>(users);
    }


}
