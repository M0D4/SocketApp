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
    
    public Client(String address, int port) throws IOException{
        
        try{
            socket = new Socket(address, port);
            System.out.println("Connected");
            
            input = new DataInputStream(System.in);
            in = new DataInputStream(socket.getInputStream());
            
            out = new DataOutputStream(socket.getOutputStream());
        }catch(UnknownHostException u){
            System.out.println(u);
        }catch(IOException io){
            System.out.println(io);
        }
        
        line = "";
        
        while(!line.equalsIgnoreCase("Break")){
            new Thread(()->{
                try{
                    line = input.readLine();
                    out.writeUTF(line);
                }catch(IOException io){
                    System.out.println(io);
                }
            }).start();
            new Thread(()->{
                try{
                    line = in.readUTF();
                    System.out.println("Server: " + line);
                }catch(IOException io){
                    System.out.println(io);
                }
            }).start();
        }
        
        //close connection
        
        try{
            input.close();
            out.close();
            socket.close();
            in.close();
        }catch(IOException io){
            System.out.println(io);
        }
    }
}
