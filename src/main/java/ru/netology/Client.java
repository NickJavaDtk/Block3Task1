package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        int count = 0;
        String host = "netology.homework";
        int port = 8889;
        try (Socket socket = new Socket(host, port);
             PrintWriter printWriter = new PrintWriter(socket.getOutputStream( ), true);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream( )));
             BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String s = bufferedReader.readLine( );
                if (s == null) {
                    break;
                }
                if (count < 2 && !s.equals("What are you doing?")) {
                    System.out.println(s);
                    String chat = bf.readLine( );
                    printWriter.println(chat);
                    count++;
                } else if (count >= 2 && !s.equals("What are you doing?") &&
                        !s.equals("And do you like your occupation? (yes/no)")) {
                    System.out.println(s);
                } else if (s.equals("What are you doing?")) {
                    System.out.println(s);
                    String chat = bf.readLine( );
                    printWriter.println(chat);
                } else if (s.equals("And do you like your occupation? (yes/no)")) {
                    System.out.println(s);
                    String chat = bf.readLine( );
                    printWriter.println(chat);
                }
            }
        } catch (IOException e) {
            e.printStackTrace( );
        }
    }
}
