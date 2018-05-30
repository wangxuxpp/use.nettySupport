package use.com.nettySupport.initialHandle;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import use.com.nettySupport.adapter.qjSignalAdapter.QJSignalChildChannelInboundHandlerAdapter;
import use.com.nettySupport.adapter.qjSignalAdapter.QJSignalChildChannelStatusSwapAdapter;
import use.com.nettySupport.initial.SocketUtil;

/**
 * 奇骏信号机 事件处理初始化
 * 项目名称:nettySupport
 * 类型名称:QJSignalChildHandleInitial
 * 类型描述:
 * 作者:wx
 * 创建时间:2017年4月20日
 * @version:
 */
@SuppressWarnings("rawtypes")
public class QJSignalChildHandleInitial extends ChannelInitializer{

	private QJSignalChildChannelStatusSwapAdapter fAdapter = new QJSignalChildChannelStatusSwapAdapter();
	
	private Class qjClass = null;
	private Class fReadIdleClass = null;
	
	public QJSignalChildHandleInitial(Class business , Class readIdleClass)
	{
		qjClass = business;
		fReadIdleClass = readIdleClass;
	}
	
	@Override
	protected void initChannel(Channel ch) throws Exception {
		
		// TODO Auto-generated method stub
		ch.pipeline().addLast(new IdleStateHandler( SocketUtil.getQJSignalParameter().getReadIdle() * 60 , 0 , 0 , TimeUnit.SECONDS));
		ByteBuf [] es = new ByteBuf [2];
		es[0]  = Unpooled.copiedBuffer("\r\n".getBytes());
		es[1]  = Unpooled.copiedBuffer("</message>".getBytes());
		
		
		ch.pipeline().addLast(this.fAdapter);
		DelimiterBasedFrameDecoder r = new DelimiterBasedFrameDecoder(1024*50 , //缓冲区大小
																	   false ,  //是否去掉结束标识
																	   es);
		ch.pipeline().addLast(r);
		ch.pipeline().addLast(new StringEncoder(Charset.forName("gb2312")));
		ch.pipeline().addLast(new StringDecoder(Charset.forName("gb2312")));
		
		ch.pipeline().addLast(new QJSignalChildChannelInboundHandlerAdapter(qjClass , fReadIdleClass));
	}

}
