package use.com.nettySupport.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import use.com.flashPolicyNetty.policy.PolicyChildHandleInitial;
import use.com.nettySupport.initial.SocketUtil;

public class FlashPolicyServer implements Runnable , ISocketServerBean{

	public final static String title = "FlashPolicyServer服务端-";
	protected final Logger log = LoggerFactory.getLogger(FlashPolicyServer.class);
	
	private int policy_port = 843;  
	private FlashPolicyServer(int port)
	{
		policy_port = port;
	}
	
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
			log.info(title+"关闭开始");
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
			
			bootStrap.childHandler(new PolicyChildHandleInitial());
			
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
			log.error(title+"over");
		}
	}
	
	public static ISocketServerBean openListener() 
	{
		FlashPolicyServer r = new FlashPolicyServer(SocketUtil.getFlashPolicyParameter().getPort());
		if(SocketUtil.getFlashPolicyParameter().isEnable())
		{
			new Thread(r).start();
		}
		return r;
	}

}
