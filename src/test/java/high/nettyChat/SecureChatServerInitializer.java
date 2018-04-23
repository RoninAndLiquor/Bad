package high.nettyChat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslContext;

public class SecureChatServerInitializer extends
		ChannelInitializer<SocketChannel> {

	private SslContext sc;
	
	public SecureChatServerInitializer(SslContext sc){
		this.sc = sc;
	}
	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		//首先添加SSL处理程序来加密和解密所有内容
		//在这个例子中，我们在服务器端使用伪造证书。
		//并接受客户端中的任何无效证书。
		//你需要更复杂的东西来识别两者。
		//和服务器在现实世界中。 
		pipeline.addLast(sc.newHandler(ch.alloc()));
		//在SSL处理程序的顶部，添加文本行编解码器。
		pipeline.addLast(new DelimiterBasedFrameDecoder(8192,Delimiters.lineDelimiter()));
		pipeline.addLast(new StringDecoder());
		pipeline.addLast(new StringEncoder());
		// and then business logic.
		pipeline.addLast(new SecureChatServerHandler());
	}

}
