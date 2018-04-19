package high.netty;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import com.couchbase.client.deps.io.netty.buffer.ByteBuf;
import com.couchbase.client.deps.io.netty.buffer.Unpooled;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

public class ChanelTest {

	public static void main(String[] args){
		Channel channel = new MyChannel(null);
		ChannelFuture future = channel.connect(new InetSocketAddress("61.135.169.125",443));
		future.addListener(new ChannelFutureListener(){

			@Override
			public void operationComplete(ChannelFuture future)
					throws Exception {
				if(future.isSuccess()){
					ByteBuf buffer =  Unpooled.copiedBuffer("HELLO",Charset.defaultCharset());
					ChannelFuture cf = future.channel().writeAndFlush(buffer);
				}else{
					Throwable cause = future.cause();
					cause.printStackTrace();
				}
			}
			
		});
	}
	
}
