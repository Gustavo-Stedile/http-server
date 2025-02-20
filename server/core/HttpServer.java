package server.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class HttpServer {
    private ServerSocket serverSocket;
    private HashMap<String, HttpMethod> routes = new HashMap<>();

    private void handleConnection(Socket clientSocket) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            HttpRequest req = new HttpRequest(in.readLine());
            HttpResponse res = new HttpResponse(clientSocket);

            if (routes.containsKey(req.route)) routes.get(req.route).action(req, res);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public void get(String route, HttpMethod httpMethod) {
        routes.put(route, httpMethod);
    }

    public void listen(int port) throws IOException {
        serverSocket = new ServerSocket(port);

        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(() -> handleConnection(socket)).start();
        }
    }
}
