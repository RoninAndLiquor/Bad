package high.netty;


import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EchoClient {

	private String host;
	private int port;
	
	public EchoClient(String host,int port){
		this.host = host;
		this.port = port;
	}
	
	public void start() throws InterruptedException{
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group)
				.channel(NioSocketChannel.class)
				//设置服务器的InetSocketAddress
				.remoteAddress(new InetSocketAddress(host,port))
				.handler(new ChannelInitializer<SocketChannel>(){

					@Override
					protected void initChannel(SocketChannel ch)
							throws Exception {
						// TODO Auto-generated method stub
						ch.pipeline().addLast(new EchoClientHandler());
					}
					
				});
			ChannelFuture f = b.connect().sync();
			System.out.println(f.isSuccess());
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			group.shutdownGracefully().sync();
		}
	}
	
	public static void main(String[] args){
		/*if(args.length!=2){
			System.err.println("Usage:"+EchoClient.class.getSimpleName()+"<host><port>");
			return;
		}*/
		//String host = args[0];
		//int port = Integer.parseInt(args[1]);
		try {
			new EchoClient("192.168.5.42",9898).start();
		} catch (InterruptedException e) {
			System.out.println("**  EchoClient  **");
			e.printStackTrace();
		}
	}
	
}
