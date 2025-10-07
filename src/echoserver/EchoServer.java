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
                Socket client = sock.accept();
                InputStream input = client.getInputStream();
                OutputStream output = client.getOutputStream();
                // System.out.println("Got a Request!");

                int fromClient; // = input.read();
                while ((fromClient = input.read()) != -1) {
                    output.write(fromClient); //.println(fromClient);
                }

                client.close();
            }
        } catch (IOException ioe){
            System.out.println("We caught and unexpected exception");
            System.err.println(ioe);
        }
    }
}