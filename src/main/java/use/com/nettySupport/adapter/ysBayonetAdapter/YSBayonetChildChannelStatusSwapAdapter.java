package use.com.nettySupport.adapter.ysBayonetAdapter;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import use.com.nettySupport.session.SocketSessionUtil;

/**
 * 宇视登录连接管理
 * 项目名称:nettySupport
 * 类型名称:YSBayonetChildChannelStatusSwapAdapter
 * 类型描述:
 * 作者:wx
 * 创建时间:2017年4月25日
 * @version:
 */
public class YSBayonetChildChannelStatusSwapAdapter extends ChannelInboundHandlerAdapter {

	/**
	 * 连接激活触发
	 */
	public void channelActive(ChannelHandlerContext ctx)
            throws Exception
    {
		SocketSessionUtil.ysBayonetSession().putChannel(ctx);
    }

	/**
	 * 连接关闭触发
	 */
    public void channelInactive(ChannelHandlerContext ctx)
        throws Exception
    {
    	SocketSessionUtil.ysBayonetSession().removeChannel(ctx);
    }
    
    public boolean isSharable()
	{
		return true;
	}
}
