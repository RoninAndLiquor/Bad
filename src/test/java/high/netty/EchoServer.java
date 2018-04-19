package high.netty;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {

	private int port;
	
	public EchoServer(int port){
		this.port = port;
	}
	
	public static void main(String[] args){
		if(args.length!=1){
			System.err.println("Usage:"+EchoServer.class.getSimpleName()+"<port>");
		}
		//int port = Integer.parseInt(args[0]);
		new EchoServer(9898).start();
	}
	
	public void start(){
		final EchoServerHandler serverHandler = new EchoServerHandler();
		//创建EventLoopGroup
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			//创建ServerBootstrap
			ServerBootstrap b = new ServerBootstrap();
			b.group(group)
				//指定使用的NIO传输Channel
				.channel(NioServerSocketChannel.class)
				//使用指定的端口设置套接字地址
				.localAddress(new InetSocketAddress(port))
				//添加一个EchoServerHandler到子Channel的ChannelPipeline
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel arg0)
							throws Exception {
						arg0.pipeline().addLast(serverHandler);//EchoServerHandler 被标注为@Shareable，所以我们可以总是使用同样的实例
					}
				});
			//异步的绑定服务器，调用sync()方法阻塞等待直至绑定完成
			ChannelFuture f = b.bind().sync();
			System.out.println(f.isSuccess());
			//获取Channel的CloseFuture，并且阻塞当前线程直至完成
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭EventLoopGroup 释放所有资源
			group.shutdownGracefully();
		}
	}
	
}
