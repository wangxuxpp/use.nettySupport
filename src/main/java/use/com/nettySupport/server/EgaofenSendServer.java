package use.com.nettySupport.server;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import protocolPackage.common.CastValue;
import protocolPackage.common.TransUtil;
import use.com.nettySupport.adapter.eGaofenAdapter.EgaofenSendImage;
import use.com.nettySupport.initial.SocketUtil;
import use.com.nettySupport.initialHandle.EgaofenChildHandleInitial;

/**
 * 易高分服务器端
 * 项目名称:use.nettySupport
 * 类型名称:EgaofenSendServer
 * 类型描述:
 * 作者:wx
 * 创建时间:2018年3月27日
 * @version:
 */
@SuppressWarnings("rawtypes")
public class EgaofenSendServer implements Runnable , ISocketServerBean{

	public static String title = "易高分";
	
	protected final Logger log = LoggerFactory.getLogger(EgaofenSendServer.class);
	
	private int policy_port = 6196;  
	
	private Class fBusinessClass = null;
	private Class fReadIdleClass = null;
	
	private EventLoopGroup bossGroup =null;
	private EventLoopGroup workerGroup = null;
	private ChannelFuture  socketChannelFuture = null;
	
	/**
	 * 构造方法
	 * @param port 监听端口
	 * @param businessClass 业务处理原类
	 * @param readIdleClass 心跳处理原类
	 */
	public EgaofenSendServer(int port , Class businessClass , Class readIdleClass)
	{
		this.policy_port = port;
		fBusinessClass = businessClass;
		fReadIdleClass = readIdleClass;
	}
	
	public void close() {
		// TODO Auto-generated method stub
		if (socketChannelFuture == null)
		{
			return;
		}
		try
		{
			log.info(title+"关闭");
			socketChannelFuture.channel().close();
			log.info(title+"关闭成功");
		}catch(Exception er)
		{
			log.info(title+"关闭失败！");
		}
	}

	public void run() {
		log.info(title+"服务线程开始启动。");
		try
		{
			bossGroup = new NioEventLoopGroup();
			workerGroup = new NioEventLoopGroup();
			ServerBootstrap bootStrap = new ServerBootstrap();
			bootStrap.group(bossGroup , workerGroup);
			bootStrap.channel(NioServerSocketChannel.class);
			bootStrap.option(ChannelOption.SO_BACKLOG, 10240);
			bootStrap.childOption(ChannelOption.TCP_NODELAY, true);
			
			bootStrap.childHandler(new EgaofenChildHandleInitial(fBusinessClass , fReadIdleClass));
			
			socketChannelFuture =bootStrap.bind(policy_port).sync();
			socketChannelFuture.channel().closeFuture().sync();

		}catch(Exception er)
		{
			log.error(title+er.getMessage());
		}
		finally
		{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
			log.info(title+"服务线程关闭。");
		}
		
	}
	
	public static ISocketServerBean openListener(Class businessClass ,Class readIdleClass) {
		EgaofenSendServer r = new EgaofenSendServer(SocketUtil.getEgaofenParameter().getPort(), businessClass , readIdleClass);
		if(SocketUtil.getEgaofenParameter().isEnable())
		{
			new Thread(r).start();
		}
		return r;
	}
	
	public static void main(String args[])
	{
		System.out.println(CastValue.egaofenRevFixEnd);
		System.out.println( Arrays.toString(TransUtil.int32ToByte4LittleEndian(0x99ab99ab)));
		System.out.println( TransUtil.ByteArrayToInt32LittleEndian(TransUtil.int32ToByte4LittleEndian(0x99ab99ab)));
		
		System.out.println(CastValue.egaofenPreFixEnd);
		System.out.println(Arrays.toString(TransUtil.int64ToByte4LittleEndian(8623117489861719978L)));
		System.out.println( TransUtil.ByteArrayToInt64LittleEndian(TransUtil.int64ToByte4LittleEndian(8623117489861719978L)));
		EgaofenSendServer r = new EgaofenSendServer(6196, EgaofenSendImage.class , null);
		r.run();
			
	}

}
