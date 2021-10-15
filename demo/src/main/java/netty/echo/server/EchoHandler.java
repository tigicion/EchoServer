package netty.echo.server;

import java.io.IOException;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws IOException{
        Request r = (Request) msg;
        Response resp = new Response();
        Servelet s = new EchoServlet();        
        s.service(r, resp);
        ctx.writeAndFlush(resp);
    }
}
