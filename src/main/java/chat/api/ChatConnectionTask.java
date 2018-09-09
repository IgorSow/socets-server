package chat.api;

import chat.domain.ChatService;
import chat.domain.model.ChatUser;
import chat.domain.port.UsersRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ChatConnectionTask implements Runnable {

    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private ChatService chatService;

    public ChatConnectionTask(Socket socket, ChatService chatService) throws IOException {
        this.socket = socket;
        this.in = new Scanner(socket.getInputStream());
        this.out = new PrintWriter(socket.getOutputStream());
        this.chatService = chatService;
    }

    private ChatUser authenticate() {
        String address = socket.getInetAddress().getHostAddress();
        String possibleName = in.nextLine() + " " + address;
        return chatService.authenticate(possibleName, address);
    }

    @Override
    public void run() {
        ChatUser user = null;
        try {
            System.out.println("Started new thread");
            user = authenticate();
            boolean flag = true;
            while (flag) {
                String massage = in.nextLine();
                System.out.println("Received message from " + user.getName() + ": " + massage);
                String response = chatService.handleMessage(massage, user);
                out.println(response);
                out.flush();
            }
        }catch (NoSuchElementException e){
            if(user != null){

                System.out.println(user.getName());
            }else{
                System.out.println("Someone disconnected");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                out.close();
                in.close();
                socket.close();
            } catch (Exception e) {

            }

        }

    }


}
