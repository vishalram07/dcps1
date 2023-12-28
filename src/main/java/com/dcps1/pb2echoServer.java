package com.dcps1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class pb2echoServer {
    public static void main(String[] args) {
        final int portNumber = 8888;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Echo Server is running on port " + portNumber);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

                String inputLine;
                while ((inputLine = input.readLine()) != null) {
                    System.out.println("Received from client: " + inputLine);

                    output.println("Server echo: " + inputLine);

                    if (inputLine.equals(".")) {
                        System.out.println("Client disconnected: " + clientSocket.getInetAddress().getHostAddress());
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

