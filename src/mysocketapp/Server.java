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

public class Server {
    private Socket socket;
    private ServerSocket server;
    private DataInputStream in, input;
    private DataOutputStream out;
    private String line;
    
    public Server(int port){
        try{
            
            server = new ServerSocket(port);
            System.out.println("Server Started and waiting for a client ...");
            
            socket = server.accept();
            
            System.out.println("Clinet Accepted");
            
            
            in = new DataInputStream(socket.getInputStream());
            input = new DataInputStream(System.in);
            
            out = new DataOutputStream(socket.getOutputStream());
            
            line = "";
            
            while(!line.equalsIgnoreCase("Break")){
                
                    new Thread(()->{
                       try{
                            line = in.readUTF();
                            System.out.println("Client: " + line);
                        }catch(IOException io){
                            System.out.println(io);
                        }
                    }).start();
                    
                    new Thread(()->{
                        try{
                            line = input.readLine();
                            out.writeUTF(line);
                        }catch(IOException io){
                            System.out.println(io);
                        }
                    }).start();
            }
            System.out.println("Closing Connection...");
            
            socket.close();
            in.close();
            input.close();
            out.close();
            
            System.out.println("Connection Closed!");
        }catch(IOException io){
            System.out.println(io);
        }
        
    }
}
