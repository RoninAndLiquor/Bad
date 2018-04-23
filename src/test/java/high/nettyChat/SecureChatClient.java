package high.nettyChat;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

public class SecureChatClient {

	static final String HOST = System.getProperty("host","127.0.0.1");
	static final int PORT = Integer.parseInt(System.getProperty("port","9898"));
	
	public static void main(String[] args) throws Exception{
		//Configure SSL
		final SslContext sc = SslContextBuilder
								.forClient()
								.trustManager(InsecureTrustManagerFactory.INSTANCE)
								.build();
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group)
				.channel(NioSocketChannel.class)
				.handler(new SecureChatClientInitializer(sc));
			Channel ch = b.connect(HOST,PORT).sync().channel();
			ChannelFuture lastWriteFuture = null;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			for(;;){
				String line = br.readLine();
				if(line == null){
					break;
				}
				lastWriteFuture = ch.writeAndFlush(line+"\r\n");
				if("bye".equals(line.toLowerCase())){
					ch.closeFuture().sync();
				}
			}
			if(lastWriteFuture!=null){
				lastWriteFuture.sync();
			}
		} catch (Exception e) {
			System.err.println(SecureChatClient.class.getSimpleName()+" Exception "+e.getMessage());
		}finally{
			group.shutdownGracefully();
		}
	}
	
}
