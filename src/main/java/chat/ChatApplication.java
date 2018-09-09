package chat;

import chat.api.ChatServer;
import chat.domain.ChatService;
import chat.domain.port.MessageRepository;
import chat.infrastructure.InMemoryMessagesRepository;
import chat.infrastructure.InMemoryUsersRepository;

import java.io.IOException;

public class ChatApplication {
    public static void main(String[] args) throws IOException {

        InMemoryUsersRepository inMemoryUsersRepository = new InMemoryUsersRepository();

        MessageRepository messageRepository = new InMemoryMessagesRepository();



        ChatService chatService = new ChatService(inMemoryUsersRepository, messageRepository);

        ChatServer chatServer = new ChatServer(chatService);

        chatServer.startServer();


    }
}
