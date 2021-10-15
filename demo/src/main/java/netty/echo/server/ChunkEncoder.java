package netty.echo.server;

import java.util.Map;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;

public class ChunkEncoder extends MessageToByteEncoder<Object>{
    private String delimeter = "\r\n";
    private byte[] rn = {'\r', '\n'};

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        // TODO Auto-generated method stub
        if(msg instanceof Header){
            StringBuilder sb = new StringBuilder();
            sb.append(((Header) msg).getProtocal()).append(delimeter);
            for(Map.Entry<String, String> e: ((Header) msg).getHm().entrySet()){
                sb.append(e.getKey()).append(": ").append(e.getValue()).append(delimeter);
    
            }
            sb.append(delimeter);
            // System.out.println(sb.toString());
            out.writeBytes(Unpooled.copiedBuffer(sb.toString(), CharsetUtil.UTF_8));
        }
        else if(msg instanceof Chunk){
            Chunk c = (Chunk) msg;
            String lengthHex = Integer.toHexString(c.getSize());
            ByteBuf buf = ctx.alloc().buffer(lengthHex.length() + 2);
            buf.writeCharSequence(lengthHex, CharsetUtil.US_ASCII);
            buf.writeBytes(rn);
            out.writeBytes(buf);
            
            StringBuilder sb = new StringBuilder();
            sb.append(c.getBody()).append(delimeter);
            out.writeBytes(Unpooled.copiedBuffer(sb.toString(), CharsetUtil.UTF_8));
        }
        
    }
    
    
}
