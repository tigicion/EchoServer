package netty.echo.server;

import java.util.HashMap;

public class Request {
    private HashMap<String,String> head;
    private String body;

    public Request(HashMap<String, String> head, String body){
        this.head = new HashMap<>(head);
        this.body=body;
    }
    public HashMap<String, String> getHead() {
        return head;
    }
    public void setHead(HashMap<String, String> head) {
        this.head = head;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }    
}
