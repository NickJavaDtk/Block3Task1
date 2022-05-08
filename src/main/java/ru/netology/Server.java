package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 8889;
        int count = 0;
        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket socket = serverSocket.accept( );
             PrintWriter printWriter = new PrintWriter(socket.getOutputStream( ), true);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream( )))) {
            System.out.println("New connection accepted");
            printWriter.println("Write your name");
            final String name = bufferedReader.readLine( );
            printWriter.println("Are you child? (yes/no)");
            while (true) {
                final String answer = bufferedReader.readLine( );
                if (answer.equals("yes")) {
                    printWriter.println(String.format("Hi %s, your port is %d", name, socket.getPort( )));
                    printWriter.println("This chat is for adults\nGoodbye!");
                    break;
                } else if (answer.equals("no") && count == 0) {
                    printWriter.println(String.format("Hi %s, your port is %d. \nWhat are you doing?", name, socket.getPort( )));
                    count++;
                }
                if (count == 1) {
                    printWriter.println("And do you like your occupation? (yes/no)");
                    count++;
                } else {
                    final String s = bufferedReader.readLine( );
                    if (s.equals("yes")) {
                        printWriter.println("Happy. I'm just counting zeros and ones." +
                                "\nGot it. \nI'll go on counting.\nGoodbye!");
                        break;
                    } else if (s.equals("no")) {
                        printWriter.println("Nothing, I'm not doing my favorite thing either.\nHold on.\nGoodbye!");
                        break;

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace( );

        }

    }

}
