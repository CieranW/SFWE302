package hw8.assignment8.src.main.java.com.assignment8.part3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class CountryClient {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: java CountryClient <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
            Socket countrySocket = new Socket(hostName, portNumber);
            PrintWriter sockOut = new PrintWriter(countrySocket.getOutputStream(), true);
            BufferedReader sockIn = new BufferedReader(new InputStreamReader(countrySocket.getInputStream()));
        ) {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer, fromUser;

            while ((fromServer = sockIn.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("Bye")) { break; }

                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    sockOut.println(fromUser);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}
