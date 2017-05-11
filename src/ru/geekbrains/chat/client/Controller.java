package ru.geekbrains.chat.client;

import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    public TextArea textArea;
    public TextField textField;
    private DataInputStream in;
    private DataOutputStream out;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connect();

    }

    public void connect(){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket("localhost",6666);
                    in = new DataInputStream(socket.getInputStream());
                    out = new DataOutputStream(socket.getOutputStream());
                    String msg = null;
                    while (!(msg = in.readUTF()).equals("/end")){
                        System.out.println(msg);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void sendMsg (){
        try {


            out.writeUTF(textField.getText());
            textField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
