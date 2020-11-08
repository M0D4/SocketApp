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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    private Socket socket;
    private ServerSocket server;
    private DataInputStream in, input;
    private DataOutputStream out;
    private String line = "";
    private Thread acceptThread;
//    private ArrayList<Client> clients;

    public Server(int port) {
        try {
//            clients = new ArrayList();
            server = new ServerSocket(port);
            System.out.println("Server Started and waiting for a client ...");
            socket = server.accept();
            System.out.println("Clinet Accepted");
            
            acceptThread = new Thread(() -> {
                while (true) {

                    try {
                        System.out.println("accepting");
                        socket = server.accept();
                        System.out.println("Clinet Accepted");
                    } catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
            acceptThread.start();
            in = new DataInputStream(socket.getInputStream());
            input = new DataInputStream(System.in);

            out = new DataOutputStream(socket.getOutputStream());

            while (!line.equalsIgnoreCase("Break")) {

                try {
                    line = in.readUTF();
                    if (line != null) {
                        out.writeUTF(line);
                    }

                    System.out.println(line);
                } catch (IOException io) {
                    System.out.println(io);
                }

            }
            System.out.println("Closing Connection...");

            socket.close();
            in.close();
            input.close();
            out.close();

            System.out.println("Connection Closed!");
        } catch (IOException io) {
            System.out.println(io);
        }

    }

//   public void subscribe(Client client){
//       clients.add(client);
//   }
//   
//   public boolean unsubscribe(Client client){
//       return clients.remove(client);
//   }
}
