package use.com.nettySupport.adapter.ysBayonetAdapter;

import io.netty.channel.ChannelHandlerContext;
import protocolPackage.ys.YSInfomationBody;

public interface IYSBayonetBusinessHandleAdapter{

	void channelRead(ChannelHandlerContext ctx, YSInfomationBody arg);
}
