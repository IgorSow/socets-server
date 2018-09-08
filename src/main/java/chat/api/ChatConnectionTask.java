package chat.api;

import chat.domain.ChatService;
import chat.domain.model.ChatUser;
import chat.domain.port.UsersRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatConnectionTask implements Runnable {

    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private UsersRepository usersRepository;
    private ChatService chatService;

    public ChatConnectionTask(Socket socket, UsersRepository usersRepository, ChatService chatService) throws IOException {
        this.socket = socket;
        this.in = new Scanner(socket.getInputStream());
        this.out = new PrintWriter(socket.getOutputStream());
        this.usersRepository = usersRepository;
        this.chatService = chatService;
    }


    @Override
    public void run() {

        try {
            ChatUser user = autheniticate();
            boolean flag = true;
            while (flag) {
                String massage = in.nextLine();
                String response = chatService.handleMessage(massage, user);
                out.println(response);
                out.flush();
            }
        } catch (Exception e) {

        } finally {
            try {

                out.close();
                in.close();
                socket.close();
            } catch (Exception e) {

            }

        }

    }


    private ChatUser autheniticate() {
        String address = socket.getInetAddress().getHostAddress();

        String posibleName = in.nextLine();
        String[] splitMassage = posibleName.split(" ");
        if ("Hello".equalsIgnoreCase(splitMassage[0])) {
            throw new InvalidCommandException("Invalid command: " + splitMassage[0]);
        }
        String name = splitMassage[1];


        ChatUser chatUser = usersRepository.find(address);
        if (chatUser == null) {
            return usersRepository.addUser(new ChatUser(name, address));
        } else {
            if (chatUser.getName().equalsIgnoreCase(name)) {
                return chatUser;
            } else {
                throw new InvalidChatUserException("Invalid name " + name);
            }
        }


    }
}
