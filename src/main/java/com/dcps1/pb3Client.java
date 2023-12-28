package com.dcps1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class pb3Client {
    public static void main(String[] args) {
        if (args.length < 1 || args.length > 2 || args[0].equalsIgnoreCase("-h") || args[0].equalsIgnoreCase("--help")) {
            System.out.println("Usage: java pb3Client.java <hostname> [port]");
            return;
        }

        String hostname = args[0];
        int port = (args.length == 2) ? Integer.parseInt(args[1]) : 1250;

        try (Socket socket = new Socket(hostname, port);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Connected to " + hostname + ":" + port);

            System.out.print("Enter username: ");
            String username = userInput.readLine();

            out.println(username);
            String response = in.readLine();
            System.out.println("Server response: " + response);

        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid port number");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
