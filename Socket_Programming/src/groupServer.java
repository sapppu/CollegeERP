import java.io.*;
import java.net.*;
import java.util.*;

public class groupServer {

    static ArrayList<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(9090);
            System.out.println("Server started on port 9090...");

            while (true) {
                Socket s = ss.accept();
                System.out.println("New client connected");

                ClientHandler ch = new ClientHandler(s);
                clients.add(ch);
                ch.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread{
    Socket s;
    DataInputStream dis;
    DataOutputStream dos;

    public ClientHandler(Socket s) throws IOException {
        this.s = s;
        dis = new DataInputStream(s.getInputStream());
        dos = new DataOutputStream(s.getOutputStream());
    }

    public void run() {
        try {
            while (true) {
                String msg = dis.readUTF();
                broadcast(msg);
            }
        } catch (Exception e) {
            System.out.println("Client disconnected");
        } finally {
            try {
                groupServer.clients.remove(this);
                dis.close();
                dos.close();
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void broadcast(String message) {
        for (ClientHandler client : groupServer.clients) {
            try {
                client.dos.writeUTF(message);
                client.dos.flush();
            } catch (Exception e) {
// ignore dead client
            }
        }
    }
}