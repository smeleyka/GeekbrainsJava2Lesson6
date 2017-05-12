package ru.geekbrains.chat.console.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by smeleyka on 12.05.17.
 */
public class Main {
    static private DataInputStream in;
    static private DataOutputStream out;
    static private Scanner sc;
    public static void main(String[] args) {
            try {
                ServerSocket socketS = new ServerSocket(6666);
                System.out.println("Server started.");
                Socket socket = socketS.accept();
                System.out.println("Client connected.");
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
                Thread outThread = new Thread(() -> {
                    try {
                        while (true) sendMsg(out);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                outThread.setDaemon(true);
                outThread.start();
                String msg = null;
                while (true){
                    msg = in.readUTF();
                    System.out.println("Client: "+msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Client disconnected.");
            }
        }
    public static void sendMsg(DataOutputStream out) throws IOException {
        sc = new Scanner(System.in);
        String outMsg = sc.nextLine();
        out.writeUTF(outMsg);
    }
}
