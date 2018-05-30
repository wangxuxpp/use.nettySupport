package use.com.nettySupport.common;

import io.netty.channel.ChannelHandlerContext;

public interface IBusinessHandlerAdapter {

	void channelRead(ChannelHandlerContext ctx, String arg);
}
