
package com.educacionit.java.customers.client;


import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class TCPClient {


    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;


    private static final Logger LOG = Logger.getLogger (TCPClient.class);


    public void startConnection (String ip, int port) {

        try {

            clientSocket = new Socket(ip, port);
            out = new PrintWriter (clientSocket.getOutputStream (), true);
            in = new BufferedReader (new InputStreamReader (clientSocket.getInputStream ()));

        } catch (IOException e) {

            LOG.debug("Error when initializing connection", e);
        }
    }

    public String sendMessage (String msg) {

        try {

            out.println(msg);
            return in.readLine();

        } catch (Exception e) {

            return null;
        }
    }

    public void stopConnection () {

        try {

            in.close ();
            out.close ();
            clientSocket.close ();

        } catch (IOException e) {

            LOG.debug("error when closing", e);
        }
    }


    public static void main (String[] args) {

        TCPClient client = new TCPClient ();
        client.startConnection ("127.0.0.1", 5555);

        String msg1 = client.sendMessage("hello");
        System.out.println (msg1);
    }
}