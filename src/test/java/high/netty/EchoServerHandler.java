package high.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

@SuppressWarnings("deprecation")
@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ByteBuf in = (ByteBuf) msg;
		System.out.println("Server Recived:"+in.toString(CharsetUtil.UTF_8));
		//将接收到的消息写给发送者，而不冲刷出站消息
		ctx.write(in);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		//将未决消息冲刷到远程节点，并且关闭改Channel
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
			.addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		//打印异常
		cause.printStackTrace();
		//关闭该Channel
		ctx.close();
	}
	
	
	
	
	
	

	
	
}
