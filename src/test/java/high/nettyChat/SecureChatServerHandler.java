package high.nettyChat;

import java.net.InetAddress;

import com.alibaba.fastjson.JSON;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.GlobalEventExecutor;

public class SecureChatServerHandler extends
		SimpleChannelInboundHandler<String> {

	static final ChannelGroup channels = new DefaultChannelGroup(
			GlobalEventExecutor.INSTANCE);

	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception {
		// 一旦会话安全，发送问候语并将信道注册到全局信道。
		// 列表中，频道接收来自其他人的消息。
		ctx.pipeline().get(SslHandler.class).handshakeFuture()
				.addListener(new GenericFutureListener<Future<Channel>>() {
					@Override
					public void operationComplete(Future<Channel> future)
							throws Exception {
						ctx.writeAndFlush("欢迎来到 "
								+ InetAddress.getLocalHost().getHostName()
								+ " 安全聊天服务!\n");
						ctx.writeAndFlush("您的会话受到 "
								+ ctx.pipeline().get(SslHandler.class).engine()
										.getSession().getCipherSuite()
								+ "加密 保护!\n");
						channels.add(ctx.channel());
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
	protected void channelRead0(ChannelHandlerContext ctx, String msg)
			throws Exception {
		// 将接收到的消息发送到当前的所有信道。 
		for (Channel channel : channels) {
			if (channel != ctx.channel()) {
				channel.writeAndFlush("[" + ctx.channel().remoteAddress() + "]说："
						+ msg + '\n');
			} else {
				channel.writeAndFlush("自己说：" + msg + '\n');
			}
		}
		System.out.println("Server recieve "+ctx.channel().remoteAddress()+":"+ msg);
		// 如果客户端发送了'bye'，则关闭连接。
		if ("bye".equals(msg.toLowerCase())) {
			ctx.close();
		}

	}

}
