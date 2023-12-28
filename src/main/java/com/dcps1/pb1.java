package com.dcps1;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class pb1 {
    public static void main(String[] args) {
        while (true) {
            try {
                System.out.print("Enter an IP address or hostname (type 'exit' to quit): ");
                String input = System.console().readLine();

                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting...");
                    break;
                }

                InetAddress[] addresses = InetAddress.getAllByName(input);

                for (InetAddress address : addresses) {
                    if (address instanceof Inet4Address) {
                        String hostname = address.getHostName();
                        System.out.println("Hostname for IPv4 address " + input + " is: " + hostname);
                    } else if (address instanceof Inet6Address) {
                        String hostname = address.getHostName();
                        System.out.println("Hostname for IPv6 address " + input + " is: " + hostname);
                    }
                }

                if (addresses.length == 0) {
                    System.out.println("No valid IP address or hostname found for input: " + input);
                }
            } catch (UnknownHostException e) {
                System.out.println("Error: Unknown host or invalid IP address");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

