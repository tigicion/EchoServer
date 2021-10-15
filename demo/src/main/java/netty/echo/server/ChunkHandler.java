package netty.echo.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ChunkHandler extends ChannelInboundHandlerAdapter{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        Request r = (Request) msg;
        int len = Integer.parseInt(r.getHead().get("Content-Length"));
        Header head = new Header();
        head.setProtocal("HTTP/1.1 200 OK");        
        head.put("Content-Type", "application/json");
        head.put("Server", "Huarx-chunked");
        head.put("Transfer-Encoding", "chunked");
        ctx.writeAndFlush(head);
        Chunk chunk = new Chunk();
        chunk.setSize(len);

        byte[] test = r.getBody().getBytes();

        System.out.println(test.length);
        chunk.setBody(r.getBody());
        ctx.writeAndFlush(chunk);
        Chunk foot = new Chunk();
        ctx.writeAndFlush(foot);
    }
    
}
