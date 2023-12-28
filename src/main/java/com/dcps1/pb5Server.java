package com.dcps1;

import java.io.*;
import java.net.*;

public class pb5Server {
    public static void main(String[] args) {
        try (DatagramSocket serverSocket = new DatagramSocket(5000)) {
            System.out.println("UDP Echo Server is running on port 5000...");

            byte[] receiveData = new byte[1024];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received from client: " + message);

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
                System.out.println("Sent back to client: " + message);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
