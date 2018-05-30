package use.com.nettySupport.initialHandle;

import java.nio.ByteOrder;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import use.com.nettySupport.adapter.eGaofenAdapter.EgaofenChildChannelInboundHandlerAdapter;
import use.com.nettySupport.adapter.eGaofenAdapter.EgaofenChildChannelStatusSwapAdapter;
import use.com.nettySupport.initial.SocketUtil;

@SuppressWarnings("rawtypes")
public class EgaofenChildHandleInitial extends ChannelInitializer{

	
	private EgaofenChildChannelStatusSwapAdapter fAdapter = new EgaofenChildChannelStatusSwapAdapter();
	
	private Class fBusinessClass = null;
	private Class fReadIdleClass = null;
	
	public EgaofenChildHandleInitial(Class businessClass , Class readIdleClass)
	{
		fBusinessClass = businessClass;
		fReadIdleClass = readIdleClass;
	}
	
	@Override
	protected void initChannel(Channel ch) throws Exception {
		
		//http://blog.csdn.net/linuu/article/details/51371595
		/** 
	     *  
	     * @param maxFrameLength 解码时，处理每个帧数据的最大长度 
	     * @param lengthFieldOffset 该帧数据中，存放该帧数据的长度的数据的起始位置 
	     * @param lengthFieldLength 记录该帧数据长度的字段本身的长度 
	     * @param lengthAdjustment 修改帧数据长度字段中定义的值，可以为负数 
	     * @param initialBytesToStrip 解析的时候需要跳过的字节数 
	     * @param failFast 为true，当frame长度超过maxFrameLength时立即报TooLongFrameException异常，为false，读取完整个帧再报异常 
	     */  
		LengthFieldBasedFrameDecoder r = new LengthFieldBasedFrameDecoder(ByteOrder.LITTLE_ENDIAN,
													1024*1024*SocketUtil.getEgaofenParameter().getMaxBuffer(),
													4,
													4,
													-8, //长度字段包含 头与长度的字段 需要从总长度中减去【】 
													0,
													true);
		ch.pipeline().addLast(this.fAdapter);
		
		/*ByteBuf [] es = new ByteBuf [1];
		/es[0]  = Unpooled.copiedBuffer(CastValue.getEgaofenPreFixEnd());
		DelimiterBasedFrameDecoder r = new DelimiterBasedFrameDecoder(1024*1024*SocketUtil.getEgaofenParameter().getMaxBuffer() ,
																		false , 
																		es);*/
		ch.pipeline().addLast(r);
		ch.pipeline().addLast(new EgaofenChildChannelInboundHandlerAdapter(fBusinessClass , fReadIdleClass));
	}
}
