package netty.echo.server;

import java.util.HashMap;

public class Response {
    private String protocal;
    private HashMap<String,String> head;
    private String body;
    public Response(){
    }
    public Response(String protocal, HashMap<String, String> head, String body){
        this.protocal=protocal;
        this.head=head;
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
    public String getProtocal() {
        return protocal;
    }
    public void setProtocal(String protocal) {
        this.protocal = protocal;
    }    
}
