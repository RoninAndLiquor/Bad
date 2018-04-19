package high.netty;

import java.net.SocketAddress;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.AbstractChannel;
import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelProgressivePromise;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoop;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

public class MyChannel extends AbstractChannel {

	protected MyChannel(Channel parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ChannelConfig config() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ChannelMetadata metadata() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AbstractUnsafe newUnsafe() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean isCompatible(EventLoop loop) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected SocketAddress localAddress0() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected SocketAddress remoteAddress0() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void doBind(SocketAddress localAddress) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doDisconnect() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doClose() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doBeginRead() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doWrite(ChannelOutboundBuffer in) throws Exception {
		// TODO Auto-generated method stub
		
	}

	

}
