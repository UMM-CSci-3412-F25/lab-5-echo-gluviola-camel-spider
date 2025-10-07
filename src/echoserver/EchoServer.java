package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static final int portNum = 6013;

    public static void main(String[] args) {
        try {
            ServerSocket sock = new ServerSocket(portNum);

            while (true) {
                Socket client = sock.accept(); // connect with client
                InputStream input = client.getInputStream();
                OutputStream output = client.getOutputStream();

                int fromClient;
                while ((fromClient = input.read()) != -1) { // while there is something to read from client:
                    output.write(fromClient); // read it and send it back to client
                }

                client.close(); // close connection
            }
        } catch (IOException ioe){
            System.out.println("We caught and unexpected exception");
            System.err.println(ioe);
        }
    }
}