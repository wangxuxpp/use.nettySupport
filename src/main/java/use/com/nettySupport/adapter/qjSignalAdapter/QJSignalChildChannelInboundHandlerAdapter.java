package use.com.nettySupport.adapter.qjSignalAdapter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import use.com.nettySupport.common.IBusinessReadIDLEAdapter;

/**
 * 奇骏信号机 SOCKET 服务端连接
 * 项目名称:nettySupport
 * 类型名称:QJSignalChildChannelInboundHandlerAdapter
 * 类型描述:
 * 作者:wx
 * 创建时间:2017年4月20日
 * @version:
 */
@SuppressWarnings({"rawtypes" })
public class QJSignalChildChannelInboundHandlerAdapter extends SimpleChannelInboundHandler<String>{

	protected final Logger log = LoggerFactory.getLogger(QJSignalChildChannelInboundHandlerAdapter.class);
	
	private IQJBusinessHandlerAdapter businessAdapter = null;
	private IBusinessReadIDLEAdapter readIdle = null;
	
	/**
	 * 奇骏信号机服务端业务处理类构造方法
	 * @param businessClass 业务处理原类
	 * @param readIdleClass 服务器心跳超时原类
	 */
	public QJSignalChildChannelInboundHandlerAdapter(Class businessClass , Class readIdleClass)
	{
		if(businessClass != null && IQJBusinessHandlerAdapter.class.isAssignableFrom(businessClass))
		{
			try
			{
				businessAdapter = (IQJBusinessHandlerAdapter)businessClass.newInstance();
			}catch(Exception er)
			{
				log.error(er.getMessage());
			}
		}
		if(readIdleClass != null && IBusinessReadIDLEAdapter.class.isAssignableFrom(readIdleClass))
		{
			try
			{
				readIdle = (IBusinessReadIDLEAdapter)readIdleClass.newInstance();
			}catch(Exception er)
			{
				log.error(er.getMessage());
			}
		}
	}
	/**
	 * 异常处理
	 */
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception
    {
    	ctx.close();
    	log.error(cause.getMessage());
    }



    /**
     * 业务处理
     */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String arg1) throws Exception {
		// TODO Auto-generated method stub
		if(businessAdapter != null)
		{
			businessAdapter.channelRead(ctx, arg1);
		}
	}
	

    /**
     * 用户自定义 事件处理
     * 处理连接长时间无数据交换断开连接
     */
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
            throws Exception
    {
    	if (evt instanceof IdleStateEvent) 
    	{
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE)
            {
            	//log.info(((InetSocketAddress)ctx.channel().remoteAddress()).getHostName()+"连接超时，断开连接");
            	//ctx.close();
            	if(readIdle!= null)
            	{
            		readIdle.userEventTriggered(ctx, evt, event);
            	}
            }
    	}else{
    		ctx.fireUserEventTriggered(evt);
    	}

    }
}
