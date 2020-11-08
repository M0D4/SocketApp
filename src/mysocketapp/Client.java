/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysocketapp;

/**
 *
 * @author Moustafa Mohamed
 */
import java.io.*;
import java.net.*;

public class Client {

    private Socket socket;
    private DataInputStream input, in;
    private DataOutputStream out;
    private String line;
    private Thread rec;
    private String name;

    public Client(String address, int port, String name) throws IOException {

        try {
            socket = new Socket(address, port);
            System.out.println("Connected");

            input = new DataInputStream(System.in);
            in = new DataInputStream(socket.getInputStream());
            this.name = name;
            out = new DataOutputStream(socket.getOutputStream());
        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException io) {
            System.out.println(io);
        }

        line = "";

        rec = new Thread(() -> {

            while (!line.equalsIgnoreCase("Break")) {
                try {
                    line = in.readUTF();
                    System.out.println(line);

                } catch (IOException io) {
                    System.out.println(io);
                }
            }

        });

        rec.start();

        while (!line.equalsIgnoreCase("Break")) {
            try {
                System.out.println("type what u want");
                line = input.readLine();
                out.writeUTF(name + " " + line);
            } catch (IOException io) {
                System.out.println(io);
            }
        }

        //close connection
        try {
            if (line.equalsIgnoreCase("break")) {
                input.close();
                out.close();
                socket.close();
                in.close();
            }
        } catch (IOException io) {
            System.out.println(io);
        }
    }
}
