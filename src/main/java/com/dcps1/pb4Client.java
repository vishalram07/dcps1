package com.dcps1;

import java.io.*;
import java.net.*;

public class pb4Client {
    public static void main(String[] args) {
        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 5000;

            // Send start message to server
            sendMessage(clientSocket, serverAddress, serverPort, "Client started");

            // Simulate some activity...
            Thread.sleep(2000);

            // Send stop message to server
            sendMessage(clientSocket, serverAddress, serverPort, "Client stopped");

        } catch (IOException | InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void sendMessage(DatagramSocket socket, InetAddress address, int port, String message) throws IOException {
        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
        socket.send(sendPacket);
        System.out.println("Sent message to server: " + message);
    }
}
