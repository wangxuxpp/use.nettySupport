package use.com.nettySupport.adapter.qjSignalAdapter;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import use.com.nettySupport.session.SocketSessionUtil;

/**
 * 
 * 项目名称:nettySupport
 * 类型名称:QJSignalChildChannelStatusSwapAdapter
 * 类型描述: 奇骏信号机接收SOCKET，连接开始、关闭触发
 * 作者:wx
 * 创建时间:2017年4月20日
 * @version:
 */
public class QJSignalChildChannelStatusSwapAdapter extends ChannelInboundHandlerAdapter {

	/**
	 * 连接激活触发
	 */
	public void channelActive(ChannelHandlerContext ctx)
            throws Exception
    {
		SocketSessionUtil.qjIOSession().putChannel(ctx);
    }

	public boolean isSharable()
	{
		return true;
	}
	/**
	 * 连接关闭触发
	 */
    public void channelInactive(ChannelHandlerContext ctx)
        throws Exception
    {
    	SocketSessionUtil.qjIOSession().removeChannel(ctx);
    }
}
