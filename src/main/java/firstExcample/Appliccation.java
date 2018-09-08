package firstExcample;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Appliccation {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8081); // -- tworzy serwer polaczenie- wystawiami polaczenie

        boolean b = true;


        while (b) {
            try{
                Socket socket = serverSocket.accept();
                System.out.println(socket.getInetAddress());
                ServerTask serverTask = new ServerTask(socket);
                new Thread(serverTask).start();
            }catch (Exception e){

            }



//            Socket socket = serverSocket.accept();     //-- polaczenie z serwerem
//
//            InputStream inputStream = socket.getInputStream();
//            Scanner scanner = new Scanner(inputStream); //-- ubieramy w skaner i czekamy
//
////            System.out.print("[" + scanner.nextLine() + "]:");
////            System.out.print(scanner.nextLine());
//
//            String name = scanner.nextLine();
//            String massage = scanner.nextLine();
//
//            System.out.println("[" + name + "]: " + massage);
//
//
//            OutputStream outputStream = socket.getOutputStream();
//
//            PrintWriter output = new PrintWriter(outputStream); // -- tworzymy obiekt ktory umozliwia zapisywanie na strumieni--
//            // scaner słuzy to zapisywania na strumien
//
//            output.println("Massage received");
//            output.flush();
//
//            socket.close();
        }
        serverSocket.close();
    }
}
