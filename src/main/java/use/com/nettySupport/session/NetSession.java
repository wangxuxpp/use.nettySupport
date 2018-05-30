package use.com.nettySupport.session;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

/**
 * 通过IP标识设备
 * 项目名称:use.nettySupport
 * 类型名称:IPNetSession
 * 类型描述:
 * 作者:wx
 * 创建时间:2018年3月27日
 * @version:
 */
public class NetSession implements INettySession{

	private ConcurrentHashMap<String , Channel> fMap = new ConcurrentHashMap<String , Channel>();

	public synchronized Channel getChannelFromId(String id) 
	{
		if(fMap.containsKey(id))
		{
			return fMap.get(id);
		}
		return null;
	}

	public synchronized List<Channel> getChannelFromIp(String ip) {
		// TODO Auto-generated method stub
		return getChannel(ip);
	}

	public synchronized void putChannel(ChannelHandlerContext ctx) 
	{
		fMap.put(ctx.channel().id().asLongText() , ctx.channel());
	}

	public synchronized void putChannel(String id, Channel ch) 
	{
		fMap.put(id , ch);	
	}
	
	@SuppressWarnings("rawtypes")
	private List<Channel> getChannel(String ip)
	{
		List<Channel> r = new ArrayList<Channel>();
		Iterator a = fMap.entrySet().iterator();
		while(a.hasNext())
		{
			Map.Entry entry = (Map.Entry) a.next(); 
			Channel c = (Channel)entry.getValue();
			String aip = ((InetSocketAddress)c.remoteAddress()).getAddress().getHostAddress();
			if(aip.equals(ip))
			{
				r.add((Channel)entry.getValue());
			}
		}
		return r;
	}

	public void removeChannel(ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub
		reChannel(ctx.channel().id().asLongText());
	}

	public void removeChannel(String id) {
		// TODO Auto-generated method stub
		reChannel(id);
	}

	public void removeChannel(Channel id) {
		// TODO Auto-generated method stub
		reChannel(id.id().asLongText());
	}
	
	private synchronized void reChannel(String id)
	{
		if(fMap.containsKey(id))
		{
			fMap.remove(id);
		}
			
	}

	public ConcurrentHashMap<String, Channel> getIOSession() {
		// TODO Auto-generated method stub
		return fMap;
	}
}
