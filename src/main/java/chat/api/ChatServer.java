package chat.api;

import chat.domain.ChatService;
import chat.domain.port.UsersRepository;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    private boolean isRunning;
    private ChatService chatService;

    public ChatServer(ChatService chatService) {
        this.chatService = chatService;
    }

    public void startServer() throws IOException {
        isRunning = true;
        ServerSocket serverSocket = new ServerSocket(8082);

        while (isRunning) {
            Socket socket = serverSocket.accept();
            ChatConnectionTask chatConnectionTask = new ChatConnectionTask(socket, chatService);
            new Thread(chatConnectionTask).start();
        }
        serverSocket.close();

    }
}
