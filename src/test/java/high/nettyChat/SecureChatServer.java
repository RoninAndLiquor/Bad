package high.nettyChat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

public class SecureChatServer {

	static final int PORT = Integer.parseInt(System.getProperty("port","9898"));
	
	public static void main(String[] args) throws Exception{
		SelfSignedCertificate ssc = new SelfSignedCertificate();
		SslContext build = SslContextBuilder
				.forServer(ssc.certificate(),ssc.privateKey()).build();
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup,workerGroup)
				.channel(NioServerSocketChannel.class)
				.handler(new LoggingHandler(LogLevel.INFO))
				.childHandler(new SecureChatServerInitializer(build));
			ChannelFuture f = b.bind(PORT).sync();
			System.out.println("SERVER BIND ON PORT : "+PORT);
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			System.err.println(SecureChatServer.class.getSimpleName()+" Exception "+e.getMessage());
		}finally{
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
	
}
