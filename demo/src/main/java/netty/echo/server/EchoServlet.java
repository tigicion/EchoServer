package netty.echo.server;

import java.io.IOException;
import java.util.HashMap;

public class EchoServlet implements Servelet{

    @Override
    public void service(Request r, Response resp) throws IOException {
        // TODO Auto-generated method stub
        HashMap<String, String> head = new HashMap<>();
        head.put("Content-Type", "application/json");
        // System.out.println(r.getHead().get("Content-Length"));
        head.put("Content-Length", r.getHead().get("Content-Length"));
        head.put("Server", "Huarx");
        resp.setProtocal("HTTP/1.1 200 OK");
        resp.setHead(head);
        resp.setBody(r.getBody());  

    }
    
}
