package high.netty2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class DiscardServer {

	private int port;
	
	public DiscardServer(int port){
		this.port = port;
	}
	
	public void run(){
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup,workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<SocketChannel>(){

					@Override
					protected void initChannel(SocketChannel ch)
							throws Exception {
						ch.pipeline().addLast(new TimeServerHandler());
					}
				}).option(ChannelOption.SO_BACKLOG, 128)
				.childOption(ChannelOption.SO_KEEPALIVE, true);
			
			//Bind and start to accept incoming connections
			ChannelFuture f = b.bind(port).sync();
			System.out.println("Server Starting on port : "+port);
			//Wait until the server socket is closed
			//In this example, this does not happen,but you can do that to gracefully
			//shutdown your server
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			System.err.println(this.getClass().getSimpleName()+" : Exception : "+e.getMessage());
		}finally{
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args){
		int port;
		if(args.length>0){
			port = Integer.parseInt(args[0]);
		}else{
			port = 9898;
		}
		new DiscardServer(port).run();
	}
	
}
