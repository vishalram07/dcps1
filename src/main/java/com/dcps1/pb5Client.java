package com.dcps1;

import java.io.*;
import java.net.*;

public class pb5Client {
    public static void main(String[] args) {
        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 5000;

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.print("Enter a message: ");
                String message = userInput.readLine();

                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
                clientSocket.send(sendPacket);
                System.out.println("Sent to server: " + message);

                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);

                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received from server: " + receivedMessage);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
