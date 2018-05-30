package use.com.nettySupport.adapter.eGaofenAdapter;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import use.com.nettySupport.session.SocketSessionUtil;

/**
 * egaofen连接管理
 * 项目名称:nettySupport
 * 类型名称:EgaofenChildChannelStatusSwapAdapter
 * 类型描述:添加链接 删除链接
 * 作者:wx
 * 创建时间:2017年9月14日
 * @version:
 */
public class EgaofenChildChannelStatusSwapAdapter extends ChannelInboundHandlerAdapter {

	/**
	 * 连接激活触发
	 */
	public void channelActive(ChannelHandlerContext ctx)
            throws Exception
    {
		SocketSessionUtil.eGaoFenSession().putChannel(ctx);
    }

	/**
	 * 连接关闭触发
	 */
    public void channelInactive(ChannelHandlerContext ctx)
        throws Exception
    {
    	SocketSessionUtil.eGaoFenSession().removeChannel(ctx);
    }
    
    public boolean isSharable()
	{
		return true;
	}
}
