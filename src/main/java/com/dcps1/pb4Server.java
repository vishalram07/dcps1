package com.dcps1;

import java.io.*;
import java.net.*;

public class pb4Server {
    public static void main(String[] args) {
        try (DatagramSocket serverSocket = new DatagramSocket(5000)) {
            System.out.println("UDP Server is running on port 5000...");

            byte[] receiveData = new byte[1024];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received from client: " + message);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
