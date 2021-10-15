package netty.echo.server;

import java.util.HashMap;

public class Header {
    private String protocal;
    private HashMap<String, String> hm;

    public Header(String protocal, HashMap<String, String> hm) {
        this.protocal = protocal;
        this.hm = hm;
    }

    public Header() {
        this("",new HashMap<String, String>());
    }
    public Header(String protocal) {
        this(protocal,new HashMap<String, String>());
    }

    public void put(String key, String value){
        this.hm.put(key, value);
    }

    public String get(String key){
        return this.hm.get(key);
    }

    public HashMap<String, String> getHm() {
        return hm;
    }

    public void setHm(HashMap<String, String> hm) {
        this.hm = hm;
    }

    public String getProtocal() {
        return protocal;
    }
    public void setProtocal(String protocal) {
        this.protocal = protocal;
    }
}
