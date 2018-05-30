package use.com.nettySupport.adapter.eGaofenAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import protocolPackage.common.TransUtil;
import protocolPackage.egaofen.EgaofenBody;
import protocolPackage.egaofen.EgaofenReadPosion;
import use.com.nettySupport.common.IBusinessReadIDLEAdapter;

/**
 * 
 * 项目名称:nettySupport
 * 类型名称:EgaofenChildChannelInboundHandlerAdapter
 * 类型描述:egaofen处理
 * 作者:wx
 * 创建时间:2017年9月14日
 * @version:
 */
@SuppressWarnings({"rawtypes" })
public class EgaofenChildChannelInboundHandlerAdapter extends SimpleChannelInboundHandler<ByteBuf>{

	protected final Logger log = LoggerFactory.getLogger(EgaofenChildChannelInboundHandlerAdapter.class);
	
	private IEGaoFenBusinessHandlerAdapter businessAdapter = null;
	private IBusinessReadIDLEAdapter readIdle = null;
	
	/**
	 * 宇视卡口数据socket 服务端
	 * @param businessClass 业务处理类原类
	 * @param readIdleClass 心跳超时原类
	 */
	public EgaofenChildChannelInboundHandlerAdapter(Class businessClass , Class readIdleClass)
	{
		if(businessClass != null && IEGaoFenBusinessHandlerAdapter.class.isAssignableFrom(businessClass))
		{
			try
			{
				businessAdapter = (IEGaoFenBusinessHandlerAdapter)businessClass.newInstance();
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
    	//ctx.close();
    	//log.error(cause.getMessage());
    	businessAdapter.exceptionCaught(ctx, cause);
    }



    /**
     * 业务处理
     */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf arg1) throws Exception {
		if(businessAdapter != null)
		{
			//命令
			byte [] r = new byte[4];
			arg1.getBytes(EgaofenReadPosion.commandPostion, r);
			int command =TransUtil.ByteArrayToInt32LittleEndian(r);// arg1.getInt(EgaofenReadPosion.commandPostion);
			//包体大小
			arg1.getBytes(EgaofenReadPosion.packageSizePostion, r);
			int packageSize = TransUtil.ByteArrayToInt32LittleEndian(r);//arg1.getInt(EgaofenReadPosion.packageSizePostion);
			
			//结束标识
			arg1.getBytes(arg1.capacity()-8, r);
			//int end = TransUtil.int64ToByte4LittleEndian(r);//arg1.getInt(arg1.capacity()-4);
			//System.out.println(end);
			//System.out.println(packageSize);
			//System.out.println(arg1.capacity());
			//if(end != CastValue.egaofenPreFixEnd || packageSize != arg1.capacity())
			//{
			//	return ;
			//}
			
			EgaofenBody fInfo = new EgaofenBody();
			
			fInfo.setCommandCode(command);
			fInfo.setPackageSize(packageSize);
			fInfo.setBodyEnd(0);
			//数据
			fInfo.setfData(arg1);
//			if(command == 1 || command ==2)
//			{
//				EgaofenQuestionBody questionInfo = new EgaofenQuestionBody();
//				//试卷ID
//				arg1.getBytes(EgaofenReadPosion.paperIdPostion, r);
//				questionInfo.setPaperId(TransUtil.ByteArrayToInt32LittleEndian(r));
//				//题号
//				arg1.getBytes(EgaofenReadPosion.questionIdPostion, r);
//				questionInfo.setQuestionId(TransUtil.ByteArrayToInt32LittleEndian(r));
//				//子题号
//				arg1.getBytes(EgaofenReadPosion.questionSectionIdPostion, r);
//				questionInfo.setQuestionSectionId(TransUtil.ByteArrayToInt32LittleEndian(r));
//				//学生SN码
//				arg1.getBytes(EgaofenReadPosion.studenSNPostion, r);
//				int snSize = TransUtil.ByteArrayToInt32LittleEndian(r);
//				ByteBuffer rb = ByteBuffer.allocate(snSize);
//				arg1.getBytes(EgaofenReadPosion.studenSNPostion+4, rb);
//				String sn = "";
//				try {
//					sn = new String(rb.array() ,"UTF-8");
//				} catch (UnsupportedEncodingException e) {
//					sn = "";
//				}
//				questionInfo.setStudenSN(sn);
//				//答案或上传图片
//				int dataSizeIndex = EgaofenReadPosion.studenSNPostion + 4 + snSize;
//				arg1.getBytes(dataSizeIndex, r);
//				int dataSize = TransUtil.ByteArrayToInt32LittleEndian(r);
//				int dataIndex = dataSizeIndex + 4;
//				ByteBuffer ans = ByteBuffer.allocate(dataSize);
//				arg1.getBytes(dataIndex, ans);
//				if(command == 1) {
//					String san = "";
//					try {
//						san = new String(ans.array() ,"UTF-8");
//					} catch (UnsupportedEncodingException e) {
//						san = "";
//					}
//					questionInfo.setAnswer(san);
//				}
//				if(command == 2){
//					questionInfo.setImageData(ans.array());
//				}
//				fInfo.setQuestBody(questionInfo);
//			}
			businessAdapter.channelRead(ctx, fInfo);
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
