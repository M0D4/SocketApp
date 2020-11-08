package mysocketapp;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Moustafa Mohamed
 */
public class MySocketApp {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        String data = sc.next();
        if (data.equalsIgnoreCase("Server")) {
            Server server = new Server(3456);
        } else {
            Client client = new Client("localhost", 3456, data);
        }
    }

}
