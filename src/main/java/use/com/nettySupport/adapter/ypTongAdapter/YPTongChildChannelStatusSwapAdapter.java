package use.com.nettySupport.adapter.ypTongAdapter;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import use.com.nettySupport.session.SocketSessionUtil;

/**
 * 元格加油连接管理
 * 项目名称:use.nettySupport
 * 类型名称:YPTongChildChannelStatusSwapAdapter
 * 类型描述:
 * 作者:wx
 * 创建时间:2018年3月27日
 * @version:
 */
public class YPTongChildChannelStatusSwapAdapter extends ChannelInboundHandlerAdapter {

	/**
	 * 连接激活触发
	 */
	public void channelActive(ChannelHandlerContext ctx)
            throws Exception
    {
		SocketSessionUtil.getYPTongSession().putChannel(ctx);
    }

	/**
	 * 连接关闭触发
	 */
    public void channelInactive(ChannelHandlerContext ctx)
        throws Exception
    {
    	SocketSessionUtil.getYPTongSession().removeChannel(ctx);
    }
    
    public boolean isSharable()
	{
		return true;
	}
}
