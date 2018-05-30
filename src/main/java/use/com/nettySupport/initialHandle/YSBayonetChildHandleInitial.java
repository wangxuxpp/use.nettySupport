package use.com.nettySupport.initialHandle;

import java.util.concurrent.TimeUnit;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import protocolPackage.common.CastValue;
import use.com.nettySupport.adapter.ysBayonetAdapter.YSBayonetChildChannelInboundHandlerAdapter;
import use.com.nettySupport.adapter.ysBayonetAdapter.YSBayonetChildChannelStatusSwapAdapter;
import use.com.nettySupport.initial.SocketUtil;

@SuppressWarnings("rawtypes")
public class YSBayonetChildHandleInitial extends ChannelInitializer{

	
	private YSBayonetChildChannelStatusSwapAdapter fAdapter = new YSBayonetChildChannelStatusSwapAdapter();
	
	private Class fBusinessClass = null;
	private Class fReadIdleClass = null;
	
	public YSBayonetChildHandleInitial(Class businessClass , Class readIdleClass)
	{
		fBusinessClass = businessClass;
		fReadIdleClass = readIdleClass;
	}
	
	@Override
	protected void initChannel(Channel ch) throws Exception {
		// TODO Auto-generated method stub

		ch.pipeline().addLast(new IdleStateHandler( SocketUtil.getYSBayontParameter().getReadIdle() * 60 , 0 , 0 , TimeUnit.SECONDS));
		ch.pipeline().addLast(this.fAdapter);
		DelimiterBasedFrameDecoder r = new DelimiterBasedFrameDecoder(1024*1024*SocketUtil.getYSBayontParameter().getReadBufferSize() ,
																		false , 
																		Unpooled.copiedBuffer(CastValue.getFixEndByte()));
		ch.pipeline().addLast(r);
		ch.pipeline().addLast(new YSBayonetChildChannelInboundHandlerAdapter(fBusinessClass , fReadIdleClass));
	}

}
