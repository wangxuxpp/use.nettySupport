package use.com.nettySupport.adapter.eGaofenAdapter;

import io.netty.channel.ChannelHandlerContext;
import protocolPackage.egaofen.EgaofenBody;

public interface IEGaoFenBusinessHandlerAdapter{
	void channelRead(ChannelHandlerContext ctx, EgaofenBody eGaoFenBody);
	void exceptionCaught(ChannelHandlerContext ctx, Throwable cause);
}
