package use.com.nettySupport.adapter.ypTongAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import protocolPackage.ypTong.YPTongBody;
import use.com.nettySupport.common.IBusinessReadIDLEAdapter;

@SuppressWarnings({"rawtypes" })
public class YPTongChildChannelInboundHandlerAdapter extends SimpleChannelInboundHandler<ByteBuf>{

	protected final Logger log = LoggerFactory.getLogger(YPTongChildChannelInboundHandlerAdapter.class);
	
	private IYPTongBusinessHandleAdapter businessAdapter = null;
	private IBusinessReadIDLEAdapter readIdle = null;
	
	/**
	 * 宇视卡口数据socket 服务端
	 * @param businessClass 业务处理类原类
	 * @param readIdleClass 心跳超时原类
	 */
	public YPTongChildChannelInboundHandlerAdapter(Class businessClass , Class readIdleClass)
	{
		if(businessClass != null && IYPTongBusinessHandleAdapter.class.isAssignableFrom(businessClass))
		{
			try
			{
				businessAdapter = (IYPTongBusinessHandleAdapter)businessClass.newInstance();
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
    	if(businessAdapter != null)
    	{
    		businessAdapter.exceptionCaught(ctx, cause);
    	}
    }



    /**
     * 业务处理
     */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf arg1) throws Exception {
		// TODO Auto-generated method stub
		if(businessAdapter != null)
		{
			YPTongBody r = new YPTongBody();
	
			r.setDeviceId(arg1.getInt(0));
			r.setCommand(arg1.getInt(4));
			r.setVersion(arg1.getInt(8));
			//数据
			byte [] aXml = new byte[arg1.capacity()-12];
			arg1.getBytes(12, aXml);
			r.setJsonStr(new String(aXml , "utf-8"));
			businessAdapter.channelRead(ctx, r);
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
            	if(readIdle != null)
            	{
            		readIdle.userEventTriggered(ctx, evt, event);
            	}
            	//log.info(((InetSocketAddress)ctx.channel().remoteAddress()).getHostName()+"连接超时，断开连接");
            	//ctx.close();
            }
    	}else{
    		ctx.fireUserEventTriggered(evt);
    	}

    }
}
