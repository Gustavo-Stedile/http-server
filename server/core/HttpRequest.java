package server.core;

public class HttpRequest {
    public final String method;
    public final String route; 

    public HttpRequest(String request) {
        String[] parts = request.split(" ");
        method = parts[0];
        route = parts[1];
    }
    
}
