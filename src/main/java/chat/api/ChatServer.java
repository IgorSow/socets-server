package chat.api;

import chat.domain.port.UsersRepository;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    private UsersRepository usersRepository;
    private boolean isRunning;

    public ChatServer(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void startServer() throws IOException {
        isRunning = true;
        ServerSocket serverSocket = new ServerSocket(8082);

        while (isRunning) {
            Socket socket = serverSocket.accept();


        }
        serverSocket.close();

    }
}
