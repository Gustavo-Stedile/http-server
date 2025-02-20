package server.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class HttpServer {
    private ServerSocket serverSocket;

    /*
    "<routes>": {
        "<route>": { "GET": <action>, "POST": <other_action> }
    }
    */
    private HashMap<String, HashMap<String, HttpMethod>> routes = new HashMap<>();

    private void handleConnection(Socket clientSocket) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            HttpRequest req = new HttpRequest(in.readLine());
            HttpResponse res = new HttpResponse(clientSocket);

            if (routes.containsKey(req.route)) 
                routes.get(req.route).get(req.method).action(req, res);

        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public void get(String route, HttpMethod httpMethod) {
        if (!routes.containsKey(route)) {
            HashMap<String, HttpMethod> method = new HashMap<>();
            method.put("GET", httpMethod);
            routes.put(route, method);
            return;
        }

        routes.get(route).put("GET", httpMethod);
    }

    public void listen(int port) throws IOException {
        serverSocket = new ServerSocket(port);

        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(() -> handleConnection(socket)).start();
        }
    }
}
