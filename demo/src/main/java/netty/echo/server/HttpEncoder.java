package netty.echo.server;

import java.util.Map;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;

public class HttpEncoder extends MessageToByteEncoder<Response>{
    private String delimeter = "\r\n";

    @Override
    protected void encode(ChannelHandlerContext ctx, Response msg, ByteBuf out) throws Exception {
        // TODO Auto-generated method stub
        StringBuilder sb = new StringBuilder();
        sb.append(msg.getProtocal()).append(delimeter);
        for(Map.Entry<String, String> e: msg.getHead().entrySet()){
            sb.append(e.getKey()).append(": ").append(e.getValue()).append(delimeter);

        }
        sb.append(delimeter).append(msg.getBody());
        System.out.println(sb.toString());
        out.writeBytes(Unpooled.copiedBuffer(sb.toString(), CharsetUtil.UTF_8));

    }

    
}
