package high.netty2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClient {

	public static void main(String[] args) {
		String host = "127.0.0.1";
		int port = Integer.parseInt("9898");
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(workerGroup);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.handler(new ChannelInitializer<SocketChannel>(){

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new TimeClientHandler());
				}
				
			});
			//Start client
			ChannelFuture f = b.connect(host,port).sync();
			//Wait until the connection is closed
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			System.err.println(TimeClient.class.getSimpleName()+" Exception "+e.getMessage());
		}finally{
			workerGroup.shutdownGracefully();
		}
	}
	
}
