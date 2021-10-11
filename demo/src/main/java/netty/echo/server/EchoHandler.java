package netty.echo.server;

import java.util.HashMap;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoHandler extends ChannelInboundHandlerAdapter{
    private String protocal="HTTP/1.0 200 OK";

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        Request r = (Request) msg;
        System.out.println("body start");
        System.out.println(r.getBody());
        HashMap<String, String> head = new HashMap<>();
        head.put("Content-Type", "application/json");
        head.put("Content-Length", String.valueOf(r.getHead().get("Content-Length")));
        head.put("Server", "Huarx");
        String body = r.getBody();
        ctx.writeAndFlush(new Response(protocal, head, body));
    }
}
