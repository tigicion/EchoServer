package netty.echo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ChunkServer {
    public void run() throws Exception{
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap s = new ServerBootstrap().group(boss, worker)
                .channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>(){
                @Override
                public void initChannel(SocketChannel ch){
                    ch.pipeline().addLast(new HttpDecoder())
                        .addLast(new ChunkEncoder())
                        .addLast(new ChunkHandler());
                    }
                });
            ChannelFuture f = s.bind(5000).sync();
            f.channel().closeFuture().sync();
        } finally{
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
        new ChunkServer().run();
    }

    
}

