package use.com.nettySupport.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import use.com.nettySupport.adapter.eGaofenAdapter.EgaofenSendImage;
import use.com.nettySupport.initial.SocketUtil;
import use.com.nettySupport.initialHandle.YPTongHandleInitial;

/**
 * 元格-油【面板与平台通讯服务端】
 * 项目名称:use.nettySupport
 * 类型名称:YPTongServer
 * 类型描述:
 * 作者:wx
 * 创建时间:2018年3月27日
 * @version:
 */
@SuppressWarnings("rawtypes")
public class YPTongServer implements Runnable , ISocketServerBean{

	public static String title = "环保液体";
	
	protected final Logger log = LoggerFactory.getLogger(YPTongServer.class);
	
	private int policy_port = 6565;  
	
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
	public YPTongServer(int port , Class businessClass , Class readIdleClass)
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
			bootStrap.option(ChannelOption.SO_BACKLOG, 1024);
			bootStrap.childOption(ChannelOption.TCP_NODELAY, true);
			
			bootStrap.childHandler(new YPTongHandleInitial(fBusinessClass , fReadIdleClass));
			
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
		YPTongServer r = new YPTongServer(SocketUtil.getYPTongHandParameter().getPort(), businessClass , readIdleClass);
		if(SocketUtil.getYPTongHandParameter().isEnable())
		{
			new Thread(r).start();
		}
		return r;
	}
	
	public static void main(String args[])
	{
		YPTongServer r = new YPTongServer(6565, EgaofenSendImage.class , null);
		r.run();			
	}
}
