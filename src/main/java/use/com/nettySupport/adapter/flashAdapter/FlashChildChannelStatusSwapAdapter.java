package use.com.nettySupport.adapter.flashAdapter;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import use.com.nettySupport.session.SocketSessionUtil;

/**
 * 
 * 项目名称:nettySupport
 * 类型名称:FlashChildChannelStatusSwapAdapter
 * 类型描述:
 * 作者:wx
 * 创建时间:2017年4月21日
 * @version:
 */
public class FlashChildChannelStatusSwapAdapter extends ChannelInboundHandlerAdapter {

	/**
	 * 连接激活触发
	 */
	public void channelActive(ChannelHandlerContext ctx)
            throws Exception
    {
		SocketSessionUtil.flashSession().putChannel(ctx);
    }

	/**
	 * 连接关闭触发
	 */
    public void channelInactive(ChannelHandlerContext ctx)
        throws Exception
    {
    	SocketSessionUtil.flashSession().removeChannel(ctx);
    }
    
    public boolean isSharable()
	{
		return true;
	}
}
