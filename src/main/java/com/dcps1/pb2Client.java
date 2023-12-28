package com.dcps1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class pb2Client {
    public static void main(String[] args) {
        if (args.length < 1 || args.length > 2 || args[0].equalsIgnoreCase("-h") || args[0].equalsIgnoreCase("--help")) {
            System.out.println("Usage: java pb2Client.java <hostname> [port]");
            return;
        }

        String hostname = args[0];
        int port = (args.length == 2) ? Integer.parseInt(args[1]) : 7;

        try (Socket socket = new Socket(hostname, port);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String userInputLine;
            while ((userInputLine = userInput.readLine()) != null) {
                if (userInputLine.equals(".")) {
                    break;
                }
                out.println(userInputLine);

                String serverResponse = in.readLine();
                System.out.println("Server: " + serverResponse);
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid port number");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
