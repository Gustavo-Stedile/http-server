package server.core;

public interface HttpMethod {
    public void action(HttpRequest req, HttpResponse res);
}
