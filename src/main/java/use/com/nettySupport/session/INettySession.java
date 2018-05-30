package use.com.nettySupport.session;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

/**
 * 通过IP标识连接
 * 项目名称:use.nettySupport
 * 类型名称:IIPNettySession
 * 类型描述:
 * 作者:wx
 * 创建时间:2018年3月27日
 * @version:
 */
public interface INettySession {

	public Channel getChannelFromId(String id);
	public List<Channel> getChannelFromIp(String ip);
	
	public void putChannel(ChannelHandlerContext ctx);
	public void putChannel(String id , Channel ch);
	
	public void removeChannel(ChannelHandlerContext ctx);
	public void removeChannel(String id);
	public void removeChannel(Channel id);
	
	public ConcurrentHashMap<String, Channel> getIOSession();
}
