package com.vi.example1.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TimeServer {

    public void bind(int port) throws Exception {
        // 配置服务端的NIO，循环事件线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();//处理登录的Reactor
        EventLoopGroup workerGroup = new NioEventLoopGroup();//处理读写的Reactor
        try {
            ServerBootstrap b = new ServerBootstrap();//配置类
            //NioServerSocketChannel作为channel类，它的功能对应于JDK NIO类库中的ServerSocketChannel
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new TimeServerHandler());//绑定事件处理类
            // 绑定端口，同步等待成功
            System.out.println("开始启动服务。。。");
            ChannelFuture f = b.bind(port).sync();
            System.out.println("启动服务完成。。。");
            // 等待服务端监听端口关闭
            f.channel().closeFuture().sync();
            System.out.println("关闭服务完成。。。");
        } finally {
            // 优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        new TimeServer().bind(port);
    }
}
