package com.dcps1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class pb3Server {
    public static void main(String[] args) {
        int port = (args.length == 1) ? Integer.parseInt(args[0]) : 1250;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("UserServer is running on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

                String username = input.readLine();

                String userData = searchUser(username);

                if (userData != null) {
                    output.println(userData);
                } else {
                    output.println("Sorry, no match to your query!");
                }

                clientSocket.close();
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid port number");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static String searchUser(String username) {
        if (username.equalsIgnoreCase("vishal")) {
            return "vishal: Vishal Ram";
        } else if (username.equalsIgnoreCase("satheesh")) {
            return "satheesh: Satheesh Kumar";
        }
        return null;
    }
}
