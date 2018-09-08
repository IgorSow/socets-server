package firstExcample;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerTask implements Runnable {

    private Socket socket;

    public ServerTask(Socket socket) {
        this.socket = socket;
    }

    public void run() {


        try {
            InputStream inputStream = socket.getInputStream();
            Scanner scanner = new Scanner(inputStream); //-- ubieramy w skaner i czekamy

            String name = scanner.nextLine();
            String massage = scanner.nextLine();

            System.out.println("[" + name + "]: " + massage);


            // ta czesc odpowiada do klienta
            OutputStream outputStream = socket.getOutputStream();

            PrintWriter output = new PrintWriter(outputStream); // -- tworzymy obiekt ktory umozliwia zapisywanie na strumieni--
            // scaner słuzy to zapisywania na strumien


            output.println("Hello here is server");
            output.flush();

        }catch (Exception e){

        }finally {

            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
