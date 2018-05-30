package use.com.nettySupport.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import use.com.nettySupport.initial.SocketUtil;
import use.com.nettySupport.initialHandle.FlashChildHandleInitial;

/**
 * Flash服务器端
 * 项目名称:use.nettySupport
 * 类型名称:FlashServer
 * 类型描述:
 * 作者:wx
 * 创建时间:2018年3月27日
 * @version:
 */
@SuppressWarnings("rawtypes")
public class FlashServer implements Runnable , ISocketServerBean{


public static String title = "flash socket服务端-";
	
	protected final Logger log = LoggerFactory.getLogger(FlashServer.class);
	
	private int policy_port = 843;
	
	private Class fBusinessClass = null;
	private Class fReadIdleClass = null;
			
	private EventLoopGroup bossGroup =null;
	private EventLoopGroup workerGroup = null;
	private ChannelFuture  socketChannelFuture = null;
	
	
	public synchronized void close() {

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
		// TODO Auto-generated method stub
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
			
			bootStrap.childHandler(new FlashChildHandleInitial(fBusinessClass , fReadIdleClass));
			
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
	
	
	private FlashServer(int p , Class businessClass , Class readIdleClass)
	{
		this.policy_port = p ;
		this.fBusinessClass = businessClass;
		this.fReadIdleClass = readIdleClass;
	}

	public static ISocketServerBean openListener(Class businessClass , Class readIdleClass) 
	{
		FlashServer r = new FlashServer(SocketUtil.getFlashParameter().getPort(), businessClass , readIdleClass);
		if(SocketUtil.getFlashParameter().isEnable())
		{
			new Thread(r).start();
		}
		return r;
	}
}
