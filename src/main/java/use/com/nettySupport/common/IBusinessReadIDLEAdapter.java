package use.com.nettySupport.common;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;

public interface IBusinessReadIDLEAdapter {

	void userEventTriggered(ChannelHandlerContext ctx, Object evt , IdleStateEvent event);
}
