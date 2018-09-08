import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.100.108", 8081);

        Scanner consolScanner = new Scanner(System.in);

        String name = consolScanner.nextLine();
        String massage = consolScanner.nextLine();

        OutputStream outputStream = socket.getOutputStream();

        PrintWriter output = new PrintWriter(outputStream); // -- tworzymy obiekt ktory umozliwia zapisywanie na strumieni--
                                                                                            // scaner słuzy to zapisywania na strumien

        output.println(name);
        output.println(massage);
        output.flush();

        Scanner scanner = new Scanner(socket.getInputStream());
        String next = scanner.nextLine();
        System.out.println(next);


        socket.close();
    }

}
