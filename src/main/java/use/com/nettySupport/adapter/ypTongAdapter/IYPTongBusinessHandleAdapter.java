package use.com.nettySupport.adapter.ypTongAdapter;

import io.netty.channel.ChannelHandlerContext;
import protocolPackage.ypTong.YPTongBody;

public interface IYPTongBusinessHandleAdapter{

	void channelRead(ChannelHandlerContext ctx, YPTongBody arg);
	void exceptionCaught(ChannelHandlerContext ctx, Throwable cause);
}
