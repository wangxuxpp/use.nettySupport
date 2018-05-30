package use.com.nettySupport.adapter.ysBayonetAdapter;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import protocolPackage.common.CastValue;
import protocolPackage.ys.YSInfomationBody;
import protocolPackage.ys.YSInformationReadPosition;
import use.com.nettySupport.common.IBusinessReadIDLEAdapter;

/**
 * 宇视卡口数据socket 服务端
 * 项目名称:nettySupport
 * 类型名称:YSBayonetChildChannelInboundHandlerAdapter
 * 类型描述:
 * 作者:wx
 * 创建时间:2017年4月25日
 * @version:
 */
@SuppressWarnings({"rawtypes" })
public class YSBayonetChildChannelInboundHandlerAdapter extends SimpleChannelInboundHandler<ByteBuf>{

	protected final Logger log = LoggerFactory.getLogger(YSBayonetChildChannelInboundHandlerAdapter.class);
	
	private IYSBayonetBusinessHandleAdapter businessAdapter = null;
	private IBusinessReadIDLEAdapter readIdle = null;
	
	/**
	 * 宇视卡口数据socket 服务端
	 * @param businessClass 业务处理类原类
	 * @param readIdleClass 心跳超时原类
	 */
	public YSBayonetChildChannelInboundHandlerAdapter(Class businessClass , Class readIdleClass)
	{
		if(businessClass != null && IYSBayonetBusinessHandleAdapter.class.isAssignableFrom(businessClass))
		{
			try
			{
				businessAdapter = (IYSBayonetBusinessHandleAdapter)businessClass.newInstance();
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
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf arg1) throws Exception {
		// TODO Auto-generated method stub
		//if(businessAdapter != null)
		{
			YSInfomationBody r = new YSInfomationBody();
			//开始标识
			r.setBodyStart(arg1.getInt(YSInformationReadPosition.packageStartOffset));
			//数据包长度
			r.setBodyLength(arg1.getInt(YSInformationReadPosition.packagelengthOffset));
			//协议版本
			r.setVersion(arg1.getInt(YSInformationReadPosition.packageVersionOffset));
			//命令码
			r.setCommandCode(arg1.getInt(YSInformationReadPosition.packageCommandCodeOffset));
			//结束表示
			r.setBodyEnd(arg1.getInt(arg1.capacity()-4));
			//数据
			ByteBuffer rb = ByteBuffer.allocate(arg1.capacity()-20);
			arg1.getBytes(YSInformationReadPosition.packageDataInfoOffset, rb);
			rb.flip();
			r.setfData(rb);
			
			if(r.getBodyEnd() == CastValue.preFixEnd && r.getBodyStart() == CastValue.preFixStart)
			{
				businessAdapter.channelRead(ctx, r);
			}
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
            	readIdle.userEventTriggered(ctx, evt, event);
            	//log.info(((InetSocketAddress)ctx.channel().remoteAddress()).getHostName()+"连接超时，断开连接");
            	//ctx.close();
            }
    	}else{
    		ctx.fireUserEventTriggered(evt);
    	}

    }
}
