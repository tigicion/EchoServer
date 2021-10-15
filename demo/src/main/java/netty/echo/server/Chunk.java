package netty.echo.server;

public class Chunk {
    private int size;
    private String body;

    public Chunk(){
        this(0, "");
    }

    public Chunk(int size, String body) {
        this.size = size;
        this.body = body;
    }    
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    
}
