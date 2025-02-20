package app;

import server.core.HttpServer;

public class Main {
    public static void main(String[] args) throws Exception {
        HttpServer server = new HttpServer();

        server.get("/", (req, res) -> {
            System.out.println(req.route);  
            res.send(
                "<head><meta charset='utf-8'/></head>" +
                "<body>" +
                    "<a href='/outra-rota'>ir para outra página</a>" +
                "</body>"
            );
        });

        server.get("/outra-rota", (req, res) -> {
            res.send(
                "<head><meta charset='utf-8'/></head>" +
                "<body>" +
                    "<h1>você chegou na outra página</h2>" +
                    "<a href='/'>voltar</a>" +
                "</body>"
            );
        });

        server.listen(80);
    }
}