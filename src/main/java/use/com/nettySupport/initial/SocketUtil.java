package use.com.nettySupport.initial;

import use.com.nettySupport.param.EgaofenParameter;
import use.com.nettySupport.param.FlashPolicyParameter;
import use.com.nettySupport.param.FlashSocketParameter;
import use.com.nettySupport.param.ISocketParameter;
import use.com.nettySupport.param.QJSignalParameter;
import use.com.nettySupport.param.YPTongHandParameter;
import use.com.nettySupport.param.YSBayonetParameter;

public class SocketUtil 
{

	public static final String title = "netty socketh_";
	
	private static FlashSocketParameter fFlashSocketParameter = new FlashSocketParameter();
	private static ISocketParameter fQJSignalParameter = new QJSignalParameter();
	private static YSBayonetParameter fYSBayontParameter = new YSBayonetParameter();
	private static EgaofenParameter fEgaofenParameter = new EgaofenParameter();
	private static FlashPolicyParameter fFlashPolicyParameter = new FlashPolicyParameter();
	
	private static YPTongHandParameter fYPTongHandParameter = new YPTongHandParameter();
	
	//宇视卡口socket参数
	public static YSBayonetParameter getYSBayontParameter()
	{
		return fYSBayontParameter;
	}
	//前台flash socket参数
	public static FlashSocketParameter getFlashParameter()
	{
		return fFlashSocketParameter;
	}
	//奇骏信号机socket参数
	public static ISocketParameter getQJSignalParameter()
	{
		return fQJSignalParameter;
	}
	//易高分参数
	public static EgaofenParameter getEgaofenParameter()
	{
		return fEgaofenParameter;
	}
	/**
	 * 加油系统参数
	 * @return
	 */
	public static YPTongHandParameter getYPTongHandParameter()
	{
		return fYPTongHandParameter;
	}
	/**
	 * flash沙箱参数
	 * @return
	 */
	public static FlashPolicyParameter getFlashPolicyParameter()
	{
		return fFlashPolicyParameter;
	}
}
