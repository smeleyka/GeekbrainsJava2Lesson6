package ru.geekbrains.chat.server;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by admin on 10.05.2017.
 */
public class Main {

    static private DataInputStream in;
    static private DataOutputStream out;

    public static void main(String[] args) {
        try {
            ServerSocket socketS = new ServerSocket(6666);
            System.out.println("Server started.");
            Socket socket = socketS.accept();
            System.out.println("Client connected.");

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            String msg = null;
            System.out.println("Перед циклом.");

            while (true){
                System.out.println("Зашел в цикл.");
                msg = in.readUTF();
                System.out.println("Client: "+msg);
                out.writeUTF("echo "+msg+"\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Client disconnected.");
        }

    }
}
