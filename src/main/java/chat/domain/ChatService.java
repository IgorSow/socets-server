package chat.domain;

import chat.domain.model.ChatCommand;
import chat.domain.model.ChatUser;
import chat.domain.port.UsersRepository;

import java.util.List;

public class ChatService {

    private UsersRepository usersRepository;

    public ChatService(UsersRepository usersRepository) {


        this.usersRepository = usersRepository;
    }

    public String handleMessage(String message, ChatUser chatUser) {
        String[] splitMessage = message.split(";");
        String command = splitMessage[0];

        ChatCommand chatCommand = ChatCommand.getByCommandName(command);

        switch (chatCommand) {
            case LIST_USERS:
                List<ChatUser> users = usersRepository.findAll();
                StringBuilder stringBuilder = new StringBuilder();

                for (ChatUser user : users) {
                    stringBuilder
                            .append(";")
                            .append(user.getName());
                }


                return stringBuilder.substring(1);

            default:
                return "";

        }

    }

}
