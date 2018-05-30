package use.com.nettySupport.adapter.eGaofenAdapter;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import protocolPackage.ypTong.YPTongBody;
import use.com.nettySupport.adapter.ypTongAdapter.IYPTongBusinessHandleAdapter;

public class EgaofenSendImage implements IYPTongBusinessHandleAdapter{

	private AtomicInteger a = new AtomicInteger();
	protected final Logger log = LoggerFactory.getLogger(EgaofenSendImage.class);


	public void exceptionCaught(ChannelHandlerContext arg0, Throwable arg1) {
		// TODO Auto-generated method stub
	
		log.error(arg1.getMessage());
		log.error(arg1.getLocalizedMessage());
	}


	public void channelRead(ChannelHandlerContext ctx, YPTongBody arg) {
		System.out.println(a.get());
		a.set(a.get()+1);
		System.out.println(arg.getJsonStr());
		
	}

}
