package netty.echo.server;

import java.util.HashMap;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;

public class HttpDecoder extends ByteToMessageDecoder{
    private HashMap<String,String> head = new HashMap<>();
    private String body="";
    private int state=0;
    private int length;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // TODO Auto-generated method stub
        
        if(state==0){
            for(;;){
                int idx = indexof(in);
                if(idx==-1) return;
                ByteBuf msg = in.readRetainedSlice(idx+2-in.readerIndex());
                String msg0 = msg.toString(CharsetUtil.UTF_8);
            
                if(msg0.equals("\r\n")){
                    state=1;
                    length = Integer.parseInt(head.get("Content-Length"));
                    return;
                }
                String[] res = msg0.split(":");
                if(res.length==1){
                    head.put("protocal", res[0].strip());
                }
                else if(res.length>=2){
                    head.put(res[0].strip(), res[1].strip());
                }
            }
            
        }
        else{
            if(in.readableBytes()>=length){
                ByteBuf b= in.readRetainedSlice(length);
                body = b.toString(CharsetUtil.UTF_8);
                Request r = new Request(head, body);  
                out.add(r);
                reset();
            }
    
        }
       
    }

    public void reset(){
        this.head.clear();
        this.body="";
        this.state=0;
        this.length=0;
    }  

    public int indexof(ByteBuf in){
        for(int i=in.readerIndex(); i<in.writerIndex(); i++){
            if(in.getByte(i)=='\r' && i+1<in.writerIndex() && in.getByte(i+1)=='\n'){
                return i;
            }
        }
        return -1;
    }
    
}
