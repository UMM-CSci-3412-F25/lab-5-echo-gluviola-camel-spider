package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.Socket;

public class EchoClient {
    public static final int portNum = 6013;

    public static void main(String[] args) throws IOException {
        String server = "127.0.0.1";
        try {
            Socket socket = new Socket(server, portNum);
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            int toServer;
            int fromServer;
            while ((toServer = System.in.read()) != -1){ // while there is still something to read:
                output.write(toServer); // send to server
                fromServer = input.read(); // get return from server
                System.out.write(fromServer); // print message from server
            }
            socket.shutdownOutput(); // tell server we are done sending

            System.out.flush(); // tell server to send any remaining content

            socket.close(); // end connection

        } catch (ConnectException ce) {
            System.out.println("Unable to connect to " + server);
            System.out.println("You should check the server is running");
        } catch (IOException ioe) {
            System.out.println("We caught an unexpected exception.");
            System.err.println(ioe);
        }
    }
}