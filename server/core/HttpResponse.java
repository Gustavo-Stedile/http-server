package server.core;

import java.io.IOException;
import java.net.Socket;

public class HttpResponse {
    private final String CRLF = "\n\r";
    private Socket clientSocket;

    public String contentType = "text/html";

    public HttpResponse(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void send(String content) {
        String response = 
        "HTTP/1.1.200 OK" + CRLF + 
        "Content-Type: " + contentType + CRLF +
        "Content-Length: " + content.length() + CRLF + 
        CRLF + content + CRLF + 
        CRLF;

        try {
            clientSocket.getOutputStream().write(response.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
