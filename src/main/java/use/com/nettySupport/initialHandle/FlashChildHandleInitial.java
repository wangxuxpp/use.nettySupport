package use.com.nettySupport.initialHandle;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import use.com.nettySupport.adapter.flashAdapter.FlashChildChannelInboundHandlerAdapter;
import use.com.nettySupport.adapter.flashAdapter.FlashChildChannelStatusSwapAdapter;
import use.com.nettySupport.initial.SocketUtil;

/**
 * flash handle初始化
 * 项目名称:nettySupport
 * 类型名称:FlashChildHandleInitial
 * 类型描述:
 * 作者:wx
 * 创建时间:2017年4月21日
 * @version:
 */
@SuppressWarnings("rawtypes")
public class FlashChildHandleInitial extends ChannelInitializer{

	private FlashChildChannelStatusSwapAdapter fAdapter = new FlashChildChannelStatusSwapAdapter();
	
	private Class fBusinessClass = null;
	private Class fReadIdleClass = null;
	
	public FlashChildHandleInitial(Class businessClass , Class readIdleClass)
	{
		fBusinessClass = businessClass;
		fReadIdleClass = readIdleClass;
	}
	
	@Override
	protected void initChannel(Channel ch) throws Exception 
	{
		if(SocketUtil.getFlashParameter().isEnableSkipHead())
		{
			ch.pipeline().addLast(new IdleStateHandler( SocketUtil.getFlashParameter().getReadIdle() * 60 , 0 , 0 , TimeUnit.SECONDS));
		}
		ch.pipeline().addLast(this.fAdapter);
		LineBasedFrameDecoder r = new LineBasedFrameDecoder(1024 * 2);
		ch.pipeline().addLast(r);
		ch.pipeline().addLast(new StringEncoder(Charset.forName("utf-8")));
		ch.pipeline().addLast(new StringDecoder(Charset.forName("utf-8")));
		ch.pipeline().addLast(new FlashChildChannelInboundHandlerAdapter(fBusinessClass , fReadIdleClass));
	}

}
