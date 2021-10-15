package netty.echo.server;

import java.util.HashMap;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;

public class HttpAggregator extends MessageToMessageDecoder<ByteBuf>{
    private HashMap<String,String> head = new HashMap<>();
    private String body="";
    private int state=0;
    private int length;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        // TODO Auto-generated method stub
        String msg0 = msg.toString(CharsetUtil.UTF_8);
        
        if(state==0){
            // System.out.println(msg0);
            // System.out.println(msg0.equals("\r\n"));
            if(msg0.equals("\r\n")){
                state=1;
                length = Integer.parseInt(head.get("Content-Length"));
                return;
            }
            String[] res = msg0.split(":");
            if(res.length==1){
                head.put("protocal", res[0]);
            }
            else if(res.length>=2){
                head.put(res[0].strip(), res[1].strip());
            }
        }
        else{
            body+=msg0;
            if(body.length()==length){
                out.add(new Request(head, body));
                reset();
            }
            else if(body.length()>length){
                throw new Exception();
            }
            System.out.println(msg0);
            System.out.println(body.length());
             
        }

        
    }

    public void reset(){
        this.head=null;
        this.body="";
        this.state=0;
        this.length=0;
    }



    
}
