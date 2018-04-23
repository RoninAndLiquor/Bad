package high.netty2;

import java.util.Date;

import com.alibaba.fastjson.JSON;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ByteBuf m = (ByteBuf) msg;
		try {
			System.out.println("CTX:"+JSON.toJSONString(ctx));
			System.out.println("MSG:"+JSON.toJSONString(msg));
			//long currentTimeMills = (m.readUnsignedInt()-2208988800L)*1000L;
			//System.out.println(new Date(currentTimeMills));
			System.out.println(m.toString(CharsetUtil.UTF_8));
			ctx.close();
		} catch (Exception e) {
			System.err.println(this.getClass().getSimpleName()+" Exception "+e.getMessage());
		}finally{
			m.release();
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	/*@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(Unpooled.copiedBuffer("THIS iS CLIENT MSG",CharsetUtil.UTF_8));
		ctx.close();
	}*/

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(Unpooled.copiedBuffer("THIS iS CLIENT MSG",CharsetUtil.UTF_8));
		ctx.close();
	}

	
	
}
