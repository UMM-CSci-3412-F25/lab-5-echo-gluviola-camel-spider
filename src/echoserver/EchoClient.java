package echoserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
    public static final int portNum = 6013;

    public static void main(String[] args) throws IOException {
        String server = "127.0.0.1";
        try {
            Socket socket = new Socket(server, portNum);
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            // BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            // BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));

            // send what we got from the keyboard to the server
            // Scanner clientInput = new Scanner(System.in);
            // Byte toServer = clientInput.nextByte();
            int toServer;
            int fromServer;
            while ((toServer = System.in.read()) != -1){
                output.write(toServer);
                fromServer = input.read();
                System.out.write(fromServer);
            }
            socket.shutdownOutput();
            
            // some sort of message to flush()?
            System.out.flush();
            // 
            socket.close();

        } catch (ConnectException ce) {
            System.out.println("Unable to connect to " + server);
            System.out.println("You should check the server is running");
        } catch (IOException ioe) {
            System.out.println("We caught an unexpected exception.");
            System.err.println(ioe);
        }
    }
}