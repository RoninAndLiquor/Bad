package high.netty2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class TimeServerHandler extends ChannelInboundHandlerAdapter{

	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception {
		final ByteBuf time = ctx.alloc().buffer(4);
		time.writeInt((int)(System.currentTimeMillis()/1000L + 2208988800L));
		//final ChannelFuture f = ctx.write(time);
		final ChannelFuture f = ctx.writeAndFlush(Unpooled.copiedBuffer("THIS IS SERVER MSG",CharsetUtil.UTF_8));
		System.out.println("Server return "+time.toString());
		f.addListener(new ChannelFutureListener(){

			@Override
			public void operationComplete(ChannelFuture future)
					throws Exception {
				assert f == future;
				ctx.close();
			}
			
		});
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ByteBuf m = (ByteBuf) msg;
		System.out.println(m.toString(CharsetUtil.UTF_8));
		ctx.close();
	}	
	
}
